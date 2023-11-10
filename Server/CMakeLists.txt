cmake_minimum_required(VERSION 3.24)
project(Server)




set(CMAKE_CXX_STANDARD 17)

add_executable(Server main.c
        main.c
        UsersLinkedList/UsersList.c
        UsersLinkedList/UsersList.h
        ServerSocket/SocketManagement.c
        ServerSocket/SocketManagement.h
        ServerSocket/SocketManagement.c
        UsersLinkedList/UserCommandsList/ComList.c
        UsersLinkedList/UserCommandsList/ComList.h
        UsersLinkedList/ActionsManagement/ActionsManagement.c
        UsersLinkedList/ActionsManagement/ActionsManagement.h
        Constants.h)
target_link_libraries(Server ws2_32)