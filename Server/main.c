#include <stdio.h>
#include <winsock2.h>
#include "UsersLinkedList/UsersList.h"
#include "ServerSocket/SocketManagement.h"
#include "UsersLinkedList/ActionsManagement/ActionsManagement.h"
#include <pthread.h>
#include "Constants.h"


int main() {
    clientList = initializeList();
    pthread_t tid;
    pthread_create(&tid, NULL, ServerThread, (void *)clientList);
    int option = -1;
    while(option != 0){
        option = mainControlMenu();
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

    wsaData = (WSADATA*) malloc(sizeof(WSADATA));
    SOCKET serverSocket, clientSocket;

    serverSocket = startUpServer(serverSocket,wsaData, serverAddr);

    newClientSocket = (SOCKET *)malloc(sizeof(SOCKET));
    while(1){
        *newClientSocket = checkNewConnection(serverSocket, clientSocket, clientAddr, clientAddrLen);
        messageFromClient = recieveFromClient(newClientSocket);
        client = checkAndUpdateUserInfo(clientList, messageFromClient);
        message = getCommandReady(client);
        sendDataToClient(newClientSocket, message);
        closesocket(*newClientSocket);
    }
}

/**
 * @brief Quick server's UI
 * @param clientList list of clients
 * @return the option selected
 */
int mainControlMenu(){
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
    free(option);
    return -1;
}

