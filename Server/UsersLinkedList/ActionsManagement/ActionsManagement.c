//
// Created by alexi on 7/11/2023.
//


#include "ActionsManagement.h"


/**
 * @brief Develops a general dialog and redirects to the actual user's target
 * @param list Linked list with all the users
 * @param operation Type of operation done, according to the menu
 */
void createObject(struct userList * list, int operation){
    printf("Id del usuario: \n");
    char * userCode = (char *)malloc(sizeof(char *));
    scanf("%s", userCode);
    struct user * userTargeted = findUserByCode(list, userCode);
    if(userTargeted == NULL){
        printf("Usuario no existente \n");
    }else{
        switch(operation){
            case 1:
                createGhostCommand(userTargeted);
                break;
            case 2:
                createPillCommand(userTargeted);
                break;
            case 3:
                createFruitCommand(userTargeted);
                break;
            case 4:
                changeGhostSpeedCommand(userTargeted);
                break;
            case 5:
                printDataFromUser(userTargeted);
            default:
                break;
        }
    }
    free(userCode);
}

/**
 * @brief sends a command to create a ghost
 * @param userTargeted user to attach the command to
 */
void createGhostCommand(struct user * userTargeted){
    char * fila = (char *)malloc(sizeof(char ));
    char * columna = (char *)malloc(sizeof(char ));
    char * message = strdup("ghost_");
    char * phantom = (char *)malloc(sizeof(char ));
    printf("Escriba el nombre del tipo de fantasma a aparecer: \n");
    scanf("%s", phantom);
    if (strcmp(phantom, "Pinky") != 0 && (strcmp(phantom, "Clyde") != 0) && (strcmp(phantom, "Inky") != 0) && (strcmp(phantom, "Blinky") != 0)){
        printf("Coloque el nombre del fantasma correcto: Pinky, Inky, Blinky o Clyde \n");
        free(fila);
        free(columna);
        free(message);
        free(phantom);
        createGhostCommand(userTargeted);
    }
    printf("Escriba la fila para aparecer: \n");
    scanf("%s", fila);
    printf("Escriba la columna para aparecer: \n");
    scanf("%s", columna);
    strcat(phantom, "_");
    strcat(phantom, fila);
    strcat(phantom, "_");
    strcat(phantom, columna);
    strcat(message, phantom);
    free(phantom);
    queueCommand(userTargeted, message);
}

/**
 * @brief sends a command to create a pill
 * @param userTargeted user to attach the command to
 */
void createPillCommand(struct user * userTargeted){
    char * fila = (char *)malloc(sizeof(char ));
    char * columna = (char *)malloc(sizeof(char ));
    char * message = strdup("pill_");
    printf("Escriba la fila para aparecer: \n");
    scanf("%s", fila);
    printf("Escriba la columna para aparecer: \n");
    scanf("%s", columna);

    strcat(columna,"_"); //columna + "_"
    strcat(columna, fila); //columna + "_" + fila
    strcat(message, columna); // operacion_columna_fila
    free(fila);
    free(columna);
    queueCommand(userTargeted, message);
}
/**
 * @brief sends a command to create a fruit
 * @param userTargeted user to attach the command to
 */
void createFruitCommand(struct user * userTargeted){
    char * fila = (char *)malloc(sizeof(char ));
    char * columna = (char *)malloc(sizeof(char ));
    printf("Escriba la fila para aparecer: \n");
    scanf("%s", fila);
    printf("Escriba la columna para aparecer: \n");
    scanf("%s", columna);
    strcat(columna,"_"); //columna + "_"
    strcat(columna, fila); //columna + "_" + fila
    char * points = (char *)malloc(sizeof(char *));
    char * message = strdup("fruit_");
    printf("Escriba la cantidad de puntos a designar a la fruta: \n");
    scanf("%s", points);
    strcat(points,"_");
    strcat(points, columna);
    strcat(message, points);
    free(points);
    queueCommand(userTargeted, message); //fuit_points_column_fila
    free(fila);
    free(columna);
}
/**
 * @brief sends a command to change the ghost speed
 * @param userTargeted user to attach the command to
 */
void changeGhostSpeedCommand(struct user * userTargeted){
    char * speed = (char *)malloc(sizeof(char *));
    char * message = strdup("speed_");
    printf("Escriba la velocidad a setear: \n");
    scanf("%s", speed);
    strcat(message, speed);
    free(speed);
    queueCommand(userTargeted, message);
}
/**
 * @brief sends the command to the user
 * @param message command to store
 * @param userTargeted user to attach the command to
 */
void queueCommand(struct user * userTargeted, char * message){
    printf("En seguida se configurara el comando... \n");
    char * suppSpace = strdup("_");
    char * suppName = strdup(userTargeted->userCode);
    strcat(suppSpace, suppName);
    strcat(message, suppSpace);
    printf("message: %s \n", message);
    addCommandLast(userTargeted->commands, createCommand(message));
    printf("Listo. El comando se ha salvado. \n");
}