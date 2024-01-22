/********************************************************************************
** Form generated from reading UI file 'mainwindow.ui'
**
** Created by: Qt User Interface Compiler version 5.15.10
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_MAINWINDOW_H
#define UI_MAINWINDOW_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QGraphicsView>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenu>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QScrollArea>
#include <QtWidgets/QSlider>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_MainWindow
{
public:
    QAction *actionOpen;
    QAction *actionSave;
    QAction *actionShutdown;
    QAction *actionSplitRgb;
    QAction *actionAveraging;
    QAction *actionGaussian;
    QAction *actionProgowanie;
    QAction *actionSkala;
    QAction *actionRotate;
    QAction *actionMedianAvg;
    QWidget *centralwidget;
    QGridLayout *gridLayout_2;
    QScrollArea *scrollArea;
    QWidget *scrollAreaWidgetContents;
    QGridLayout *gridLayout;
    QGraphicsView *graphicsView;
    QHBoxLayout *horizontalLayout;
    QSlider *contrastR;
    QSlider *contrastG;
    QSlider *contrastB;
    QSlider *brightnessSlider;
    QPushButton *displayContrast;
    QMenuBar *menubar;
    QMenu *menuMENU;
    QMenu *menuPrzetwarzanie_obraz_w;
    QStatusBar *statusbar;
    QToolBar *toolBar;

    void setupUi(QMainWindow *MainWindow)
    {
        if (MainWindow->objectName().isEmpty())
            MainWindow->setObjectName(QString::fromUtf8("MainWindow"));
        MainWindow->resize(800, 600);
        actionOpen = new QAction(MainWindow);
        actionOpen->setObjectName(QString::fromUtf8("actionOpen"));
        QIcon icon;
        icon.addFile(QString::fromUtf8(":/icons/folder-open.png"), QSize(), QIcon::Normal, QIcon::Off);
        actionOpen->setIcon(icon);
        actionSave = new QAction(MainWindow);
        actionSave->setObjectName(QString::fromUtf8("actionSave"));
        QIcon icon1;
        icon1.addFile(QString::fromUtf8(":/icons/save.png"), QSize(), QIcon::Normal, QIcon::Off);
        actionSave->setIcon(icon1);
        actionShutdown = new QAction(MainWindow);
        actionShutdown->setObjectName(QString::fromUtf8("actionShutdown"));
        QIcon icon2;
        icon2.addFile(QString::fromUtf8(":/icons/x-octagon.png"), QSize(), QIcon::Normal, QIcon::Off);
        actionShutdown->setIcon(icon2);
        actionSplitRgb = new QAction(MainWindow);
        actionSplitRgb->setObjectName(QString::fromUtf8("actionSplitRgb"));
        QIcon icon3;
        icon3.addFile(QString::fromUtf8(":/icons/wrench.png"), QSize(), QIcon::Normal, QIcon::Off);
        actionSplitRgb->setIcon(icon3);
        actionAveraging = new QAction(MainWindow);
        actionAveraging->setObjectName(QString::fromUtf8("actionAveraging"));
        actionGaussian = new QAction(MainWindow);
        actionGaussian->setObjectName(QString::fromUtf8("actionGaussian"));
        actionProgowanie = new QAction(MainWindow);
        actionProgowanie->setObjectName(QString::fromUtf8("actionProgowanie"));
        actionSkala = new QAction(MainWindow);
        actionSkala->setObjectName(QString::fromUtf8("actionSkala"));
        actionRotate = new QAction(MainWindow);
        actionRotate->setObjectName(QString::fromUtf8("actionRotate"));
        actionMedianAvg = new QAction(MainWindow);
        actionMedianAvg->setObjectName(QString::fromUtf8("actionMedianAvg"));
        centralwidget = new QWidget(MainWindow);
        centralwidget->setObjectName(QString::fromUtf8("centralwidget"));
        gridLayout_2 = new QGridLayout(centralwidget);
        gridLayout_2->setObjectName(QString::fromUtf8("gridLayout_2"));
        scrollArea = new QScrollArea(centralwidget);
        scrollArea->setObjectName(QString::fromUtf8("scrollArea"));
        scrollArea->setMinimumSize(QSize(200, 200));
        scrollArea->setWidgetResizable(true);
        scrollAreaWidgetContents = new QWidget();
        scrollAreaWidgetContents->setObjectName(QString::fromUtf8("scrollAreaWidgetContents"));
        scrollAreaWidgetContents->setGeometry(QRect(0, 0, 778, 484));
        gridLayout = new QGridLayout(scrollAreaWidgetContents);
        gridLayout->setObjectName(QString::fromUtf8("gridLayout"));
        graphicsView = new QGraphicsView(scrollAreaWidgetContents);
        graphicsView->setObjectName(QString::fromUtf8("graphicsView"));

        gridLayout->addWidget(graphicsView, 0, 0, 1, 1);

        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setObjectName(QString::fromUtf8("horizontalLayout"));
        contrastR = new QSlider(scrollAreaWidgetContents);
        contrastR->setObjectName(QString::fromUtf8("contrastR"));
        contrastR->setMinimum(-100);
        contrastR->setMaximum(100);
        contrastR->setOrientation(Qt::Horizontal);

        horizontalLayout->addWidget(contrastR);

        contrastG = new QSlider(scrollAreaWidgetContents);
        contrastG->setObjectName(QString::fromUtf8("contrastG"));
        contrastG->setMinimum(-100);
        contrastG->setMaximum(100);
        contrastG->setOrientation(Qt::Horizontal);

        horizontalLayout->addWidget(contrastG);

        contrastB = new QSlider(scrollAreaWidgetContents);
        contrastB->setObjectName(QString::fromUtf8("contrastB"));
        contrastB->setMinimum(-100);
        contrastB->setMaximum(100);
        contrastB->setOrientation(Qt::Horizontal);

        horizontalLayout->addWidget(contrastB);

        brightnessSlider = new QSlider(scrollAreaWidgetContents);
        brightnessSlider->setObjectName(QString::fromUtf8("brightnessSlider"));
        brightnessSlider->setMinimum(50);
        brightnessSlider->setMaximum(299);
        brightnessSlider->setValue(150);
        brightnessSlider->setOrientation(Qt::Horizontal);

        horizontalLayout->addWidget(brightnessSlider);

        displayContrast = new QPushButton(scrollAreaWidgetContents);
        displayContrast->setObjectName(QString::fromUtf8("displayContrast"));

        horizontalLayout->addWidget(displayContrast);


        gridLayout->addLayout(horizontalLayout, 1, 0, 1, 1);

        scrollArea->setWidget(scrollAreaWidgetContents);

        gridLayout_2->addWidget(scrollArea, 0, 0, 1, 1);

        MainWindow->setCentralWidget(centralwidget);
        menubar = new QMenuBar(MainWindow);
        menubar->setObjectName(QString::fromUtf8("menubar"));
        menubar->setGeometry(QRect(0, 0, 800, 26));
        menuMENU = new QMenu(menubar);
        menuMENU->setObjectName(QString::fromUtf8("menuMENU"));
        menuPrzetwarzanie_obraz_w = new QMenu(menubar);
        menuPrzetwarzanie_obraz_w->setObjectName(QString::fromUtf8("menuPrzetwarzanie_obraz_w"));
        MainWindow->setMenuBar(menubar);
        statusbar = new QStatusBar(MainWindow);
        statusbar->setObjectName(QString::fromUtf8("statusbar"));
        MainWindow->setStatusBar(statusbar);
        toolBar = new QToolBar(MainWindow);
        toolBar->setObjectName(QString::fromUtf8("toolBar"));
        MainWindow->addToolBar(Qt::TopToolBarArea, toolBar);

        menubar->addAction(menuMENU->menuAction());
        menubar->addAction(menuPrzetwarzanie_obraz_w->menuAction());
        menuMENU->addAction(actionOpen);
        menuMENU->addAction(actionSave);
        menuMENU->addAction(actionShutdown);
        menuPrzetwarzanie_obraz_w->addAction(actionSplitRgb);
        menuPrzetwarzanie_obraz_w->addSeparator();
        menuPrzetwarzanie_obraz_w->addAction(actionProgowanie);
        menuPrzetwarzanie_obraz_w->addAction(actionSkala);
        menuPrzetwarzanie_obraz_w->addAction(actionRotate);
        menuPrzetwarzanie_obraz_w->addSeparator();
        menuPrzetwarzanie_obraz_w->addAction(actionAveraging);
        menuPrzetwarzanie_obraz_w->addAction(actionGaussian);
        menuPrzetwarzanie_obraz_w->addAction(actionMedianAvg);
        toolBar->addAction(actionOpen);
        toolBar->addAction(actionSave);
        toolBar->addSeparator();
        toolBar->addAction(actionShutdown);
        toolBar->addSeparator();
        toolBar->addAction(actionSplitRgb);

        retranslateUi(MainWindow);

        QMetaObject::connectSlotsByName(MainWindow);
    } // setupUi

    void retranslateUi(QMainWindow *MainWindow)
    {
        MainWindow->setWindowTitle(QCoreApplication::translate("MainWindow", "MainWindow", nullptr));
        actionOpen->setText(QCoreApplication::translate("MainWindow", "&Otw\303\263rz", nullptr));
#if QT_CONFIG(shortcut)
        actionOpen->setShortcut(QCoreApplication::translate("MainWindow", "Ctrl+O", nullptr));
#endif // QT_CONFIG(shortcut)
        actionSave->setText(QCoreApplication::translate("MainWindow", "&Zapisz", nullptr));
#if QT_CONFIG(shortcut)
        actionSave->setShortcut(QCoreApplication::translate("MainWindow", "Ctrl+S", nullptr));
#endif // QT_CONFIG(shortcut)
        actionShutdown->setText(QCoreApplication::translate("MainWindow", "Zako\305\204cz", nullptr));
#if QT_CONFIG(shortcut)
        actionShutdown->setShortcut(QCoreApplication::translate("MainWindow", "Ctrl+F4", nullptr));
#endif // QT_CONFIG(shortcut)
        actionSplitRgb->setText(QCoreApplication::translate("MainWindow", "Rozdziel na RGB", nullptr));
#if QT_CONFIG(shortcut)
        actionSplitRgb->setShortcut(QCoreApplication::translate("MainWindow", "Ctrl+P", nullptr));
#endif // QT_CONFIG(shortcut)
        actionAveraging->setText(QCoreApplication::translate("MainWindow", "U\305\233rednianie", nullptr));
        actionGaussian->setText(QCoreApplication::translate("MainWindow", "Rozmycie Gausowskie", nullptr));
        actionProgowanie->setText(QCoreApplication::translate("MainWindow", "Progowanie obrazu", nullptr));
        actionSkala->setText(QCoreApplication::translate("MainWindow", "Skala", nullptr));
        actionRotate->setText(QCoreApplication::translate("MainWindow", "Obr\303\263t", nullptr));
        actionMedianAvg->setText(QCoreApplication::translate("MainWindow", "U\305\233rednianie medianowe", nullptr));
        displayContrast->setText(QCoreApplication::translate("MainWindow", "Show", nullptr));
        menuMENU->setTitle(QCoreApplication::translate("MainWindow", "Plik", nullptr));
        menuPrzetwarzanie_obraz_w->setTitle(QCoreApplication::translate("MainWindow", "Przetwarzanie obraz\303\263w", nullptr));
        toolBar->setWindowTitle(QCoreApplication::translate("MainWindow", "toolBar", nullptr));
    } // retranslateUi

};

namespace Ui {
    class MainWindow: public Ui_MainWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MAINWINDOW_H
