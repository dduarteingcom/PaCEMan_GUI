//
// Created by alexi on 6/11/2023.
//

#ifndef SERVER_USERSLIST_H
#define SERVER_USERSLIST_H

typedef struct user{
    int * userCode;
    struct user * nextUser;
}user;

typedef struct userList{
    int * length;
    struct user * head;
}userList;

struct user * createUser(int userCode);
struct userList * initializeList();
void addNodeLast(struct userList * lista, struct user * newUser);
void printUserList(struct userList * lista);
struct user * findUserByCode(struct userList * list, int code);
struct user * findUserByIndex(struct userList * list, int index);

#endif //SERVER_USERSLIST_H
