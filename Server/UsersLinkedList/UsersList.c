//
// Created by alexi on 6/11/2023.
//
#include <stdio.h>
#include <malloc.h>
#include <string.h>
#include "UsersList.h"



struct user * createUser(char * userCode){
    struct user * newUser = (struct user *)malloc(sizeof(struct user));
    newUser->commands = initializeCommandList();

    newUser->score = (int *) malloc(sizeof(int));
    newUser->lives = (int *) malloc(sizeof(int));
    newUser->level = (int *) malloc(sizeof(int));
    newUser->speed = (int *) malloc(sizeof(int));

    *(newUser->score) = 0;
    *(newUser->lives) = 0;
    *(newUser->level) = 0;
    *(newUser->speed) = 0;

    newUser->userCode = userCode;
    newUser->nextUser = NULL;
    return newUser;
}

struct userList * initializeList(){
    struct userList * newList = (struct userList *) malloc(sizeof (struct userList));
    int * listLenght = (int *)malloc(sizeof (int));
    *(listLenght) = 0;
    newList->length = listLenght;
    newList->head = NULL;
    return newList;
}

void addNodeLast(struct userList * lista, struct user * newUser){
    if(*(lista->length) == 0){
        lista->head = newUser;
    }else{
        struct user * temp = lista->head;
        while(temp->nextUser != NULL ){
            temp = temp->nextUser;
        }
        temp->nextUser = newUser;
    }
    (*(lista->length))++;
}

void printUserList(struct userList * lista){
    printf("++++++++++++++++++++++ \n");
    if(*(lista->length) > 0){
        struct user * temp = lista->head;

        for (int i = 0; i < (*(lista->length)); ++i) {
            printf("User: %s \n", temp->userCode);
            printCommandList(temp->commands);
            temp = temp->nextUser;
        }
    }else{
        printf("Lista vacia. \n");
    }
    printf("++++++++++++++++++++++ \n");
}

struct user * findUserByCode(struct userList * list, char * code){
    if(*(list->length) == 0){
        return NULL;
    }else{
        struct user * temp = list->head;
        while(temp->nextUser != NULL){
            if(strcmp(temp->userCode, code) ==0){
                return temp;
            }
            temp = temp->nextUser;
        }
        if(strcmp(temp->userCode, code) ==0){
            return temp;
        }
    }
    return NULL;
}

struct user * checkAndUpdateUserInfo(struct userList * list,char * infoFromClient){
    char * copy = strdup(infoFromClient);
    char * data = strtok(copy, "_");
    struct user * client = findUserByCode(list, data);
    if(client == NULL){
        char * userCode = strdup(data);
        client = createUser(userCode);
        addNodeLast(list, client);
    }
    int i = 0;
    while(data != NULL){
        switch (i) {
            case 1:
                *(client->score) = atoi(data);
                break;
            case 2:
                *(client->lives) = atoi(data);
                break;
            case 3:
                *(client->level) = atoi(data);
                break;
            case 4:
                *(client->speed) = atoi(data);
                break;
        }
        i++;
        data = strtok(NULL, "_");
    }
    free(copy);
    return client;
}

char * getCommandReady(struct user * client){
    if(*(client->commands->length) == 0){
        return strdup("void_");
    }else{
        char * message = strdup(client->commands->head->text);
        deleteHeadCommand(client->commands);
        return message;
    }
}
