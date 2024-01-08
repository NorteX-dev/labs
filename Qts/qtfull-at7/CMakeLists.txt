cmake_minimum_required(VERSION 3.5)

project(qtfull VERSION 0.1 LANGUAGES CXX)

set(CMAKE_AUTOUIC ON)
set(CMAKE_AUTOMOC ON)
set(CMAKE_AUTORCC ON)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

find_package(QT NAMES Qt6 Qt5 REQUIRED COMPONENTS Widgets)
find_package(Qt${QT_VERSION_MAJOR} REQUIRED COMPONENTS Widgets)

# Add OpenCV
find_package(OpenCV REQUIRED)

set(PROJECT_SOURCES
    main.cpp
    mainwindow.cpp
    mainwindow.h
    mainwindow.ui
)

if(${QT_VERSION_MAJOR} GREATER_EQUAL 6)
    qt_add_executable(qtfull
        MANUAL_FINALIZATION
        ${PROJECT_SOURCES}
    )
else()
    if(ANDROID)
        add_library(qtfull SHARED
            ${PROJECT_SOURCES}
        )
    else()
        add_executable(qtfull
            ${PROJECT_SOURCES}
            icons.qrc

        )
    endif()
endif()

# Link against OpenCV
target_link_libraries(qtfull PRIVATE Qt${QT_VERSION_MAJOR}::Widgets ${OpenCV_LIBS})

if(QT_VERSION_MAJOR EQUAL 6)
    qt_finalize_executable(qtfull)
endif()
