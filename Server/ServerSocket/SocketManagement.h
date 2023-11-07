//
// Created by alexi on 6/11/2023.
//

#ifndef SERVER_SOCKETMANAGEMENT_H
#define SERVER_SOCKETMANAGEMENT_H
#include <winsock2.h>
#include <stdio.h>

//Variables


//Functions
SOCKET startUpServer(SOCKET serverSocket, WSADATA * wsaData, struct sockaddr_in serverAddr);
SOCKET checkNewConnection(SOCKET serverSocket, SOCKET clientSocket, struct sockaddr_in clientAddr, int clientAddrLen);
char * recieveFromClient(const SOCKET * newClientSocket);
void sendDataToClient(const SOCKET * clientSocket, const char * message);
void closingProtocol(SOCKET serverSocket, char * errorMessage);

#endif //SERVER_SOCKETMANAGEMENT_H
