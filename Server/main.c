#include <stdio.h>
#include <winsock2.h>
#include "UsersLinkedList/UsersList.h"
#include "ServerSocket/SocketManagement.h"
#include "UsersLinkedList/ActionsManagement/ActionsManagement.h"
#include <pthread.h>

void * ServerThread(void *vargp);
int mainControlMenu(struct userList * clientList);
struct userList * clientList;

int main() {
    clientList = initializeList();
    pthread_t tid;
    pthread_create(&tid, NULL, ServerThread, (void *)clientList);
    int option = -1;
    while(option != 0){
        option = mainControlMenu(clientList);
    }
    pthread_exit(NULL);
    free(clientList);
    return 0;
}

/**
 * @brief Server's communication thread
 * @param vargp
 * @return 
 */
void * ServerThread(void *vargp){

    WSADATA * wsaData = (WSADATA*) malloc(sizeof(WSADATA));
    SOCKET serverSocket, clientSocket;
    struct sockaddr_in serverAddr, clientAddr;
    int clientAddrLen = sizeof(clientAddr);

    serverSocket = startUpServer(serverSocket,wsaData, serverAddr);

    SOCKET * newClientSocket = (SOCKET *)malloc(sizeof(SOCKET));
    *newClientSocket = checkNewConnection(serverSocket, clientSocket, clientAddr, clientAddrLen);
    char * messageFromClient;

    while(1){
        messageFromClient = recieveFromClient(newClientSocket);
        struct user * client = checkAndUpdateUserInfo(clientList, messageFromClient);
        char * message = getCommandReady(client);
        sendDataToClient(newClientSocket, message);
        free(message);
        closesocket(*newClientSocket);
        *newClientSocket = checkNewConnection(serverSocket, clientSocket, clientAddr, clientAddrLen);
    }
}

/**
 * @brief Quick server's UI
 * @param clientList list of clients
 * @return the option selected
 */
int mainControlMenu(struct userList * clientList){
    int * option = (int *)malloc(sizeof(int));
    printf("Opcion a ejecutar: \n 0 - Salir \n 1 - Crear fantasma \n 2 - Crear partilla \n"
           "3 - Crear fruta \n 4 - Cambiar velocidad de fantasmas \n 5 - Imprimir"
           " datos de un usuario \n 6 - Imprimir lista de clientes \n");
    scanf("%d", option);
    if(*option == 0){
        printf("Adios :(");
        free(option);
        return 0;
    }else if(*option > 0 && *option < 6) {
        createObject(clientList, *option);
    }else if(*option == 6){
        printUserList(clientList);
    }else{
        printf("Opción inválida \n");
    }

    return -1;
}

