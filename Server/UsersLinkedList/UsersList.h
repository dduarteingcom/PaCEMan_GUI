//
// Created by alexi on 6/11/2023.
//

#ifndef SERVER_USERSLIST_H
#define SERVER_USERSLIST_H
#include <winsock2.h>
typedef struct user{
    char * userCode;
    SOCKET * userSocket;
    struct user * nextUser;
}user;

typedef struct userList{
    int * length;
    struct user * head;
}userList;

struct user * createUser(char userCode);
struct userList * initializeList();
void addNodeLast(struct userList * lista, struct user * newUser);
void printUserList(struct userList * lista);
struct user * findUserByCode(struct userList * list, char code);
struct user * findUserByIndex(struct userList * list, int index);


#endif //SERVER_USERSLIST_H
