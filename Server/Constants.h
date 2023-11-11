//
// Created by alexi on 9/11/2023.
//

#ifndef SERVER_CONSTANTS_H
#define SERVER_CONSTANTS_H

#include <winsock2.h>

//Global absolute constants
void * ServerThread(void *vargp);
int mainControlMenu();
struct userList * clientList;

//Thread Constants
char * messageFromClient;
char * message;
struct user * client;
SOCKET * newClientSocket;
struct sockaddr_in serverAddr, clientAddr;
int clientAddrLen = sizeof(clientAddr);
WSADATA * wsaData;

//Main control constants

#endif //SERVER_CONSTANTS_H
