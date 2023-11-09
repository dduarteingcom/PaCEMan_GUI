//
// Created by alexi on 6/11/2023.
//
#include <stdio.h>
#include <malloc.h>
#include <string.h>
#include "UsersList.h"


/**
 * @brief Creates an user struct, with memory allocation
 * @param userCode player's name or identification
 * @return new user created
 */
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

/**
 * @brief initializes a command list
 * @return the new list initialized
 */
struct userList * initializeList(){
    struct userList * newList = (struct userList *) malloc(sizeof (struct userList));
    int * listLenght = (int *)malloc(sizeof (int));
    *(listLenght) = 0;
    newList->length = listLenght;
    newList->head = NULL;
    return newList;
}

/**
* @brief Adds node to the bottom of the list
* @param list list of commands
* @param newUser user to add
*/
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

/**
 * @brief prints the full length of the list, including any posible sublist inside
 * @param lista list to print
 */
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
/**
 * @brief prints the data from an specific user
 * @param userTargeted user to print
 */
void printDataFromUser(struct user * userTargeted){
    printf("......................................... \n");
    if(userTargeted != NULL){
        printf("User: %s \n", userTargeted->userCode);
        printf("To next life: %d \n", *(userTargeted->lives));
        printf("Score: %d \n", *(userTargeted->score));
        printf("To next level: %d \n", *(userTargeted->level));
        printf("Ghost speed: %d \n", *(userTargeted->speed));
    }else{
        printf("No hay tal usuario registrado \n");
    }
    printf("......................................... \n");
}

/**
 * @brief finds an user based on its code
 * @param list list to find in
 * @param code name of the client
 * @return user found
 */
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

/**
 * @brief checks the info in a message from the client. Adds the user if it's new, and updates the information with the
 * arraving data
 * @param list list to
 * @param infoFromClient name of the client (userCode)
 * @return user created (or updated)
 */
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
                *(client->score) = (atoi(data))*10;
                break;
            case 2:
                *(client->lives) = atoi(data)*10;
                if(*(client->lives) >= 10000){
                    char * message = strdup("addLife_");
                    char * suppName = strdup(client->userCode);
                    strcat(message, suppName);
                    addCommandFirst(client->commands, createCommand(message));
                    free(suppName);
                }
                break;
            case 3:
                *(client->level) = atoi(data);
                if(*(client->level) <= 0){
                    char * message = strdup("next_");
                    char * suppName = strdup(client->userCode);
                    strcat(message, suppName);
                    addCommandFirst(client->commands, createCommand(message));
                    free(suppName);
                }
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

/**
 * @brief looks and returns any pendant command for the client
 * @param client client to evaluate
 * @return char array with the command, or "void_" if there is no pendant command.
 */
char * getCommandReady(struct user * client){
    if(*(client->commands->length) == 0){
        return strdup("void_");
    }else{
        char * message = strdup(client->commands->head->text);
        deleteHeadCommand(client->commands);
        return message;
    }
}

void addSpecialCommand(struct user * client, char * string){

}