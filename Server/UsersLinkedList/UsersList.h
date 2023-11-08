//
// Created by alexi on 6/11/2023.
//

#ifndef SERVER_USERSLIST_H
#define SERVER_USERSLIST_H
#include "UserCommandsList/ComList.h"
typedef struct user{
    const char * userCode;
    struct user * nextUser;
    struct commandList * commands;
    int * lives;
    int * score;
    int * level;
    int * speed;
}user;

typedef struct userList{
    int * length;
    struct user * head;
}userList;

struct user * createUser(char * userCode);
struct userList * initializeList();
void addNodeLast(struct userList * lista, struct user * newUser);
void printUserList(struct userList * lista);
struct user * findUserByCode(struct userList * list, char * code);
struct user * checkAndUpdateUserInfo(struct userList * list , char * infoFromClient);
char * getCommandReady(struct user * client);

#endif //SERVER_USERSLIST_H
