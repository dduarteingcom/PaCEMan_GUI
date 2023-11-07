//
// Created by alexi on 6/11/2023.
//
#include <stdio.h>
#include <malloc.h>
#include <string.h>
#include "UsersList.h"



struct user * createUser(char * userCode){
    struct user * newUser = (struct user *)malloc(sizeof(struct user));

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
        printf("Here 2 \n");
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

struct user * findUserByIndex(struct userList * list, int index){
    if(*(list->length) == 0){
        return NULL;
    }else{
        struct user * temp = list->head;
        int i = 0;
        while(temp->nextUser != NULL){
            if(i == index){
                return temp;
            }
            i++;
            temp = temp->nextUser;
        }
        if(index == i){
            return temp;
        }
    }
    return NULL;
}
