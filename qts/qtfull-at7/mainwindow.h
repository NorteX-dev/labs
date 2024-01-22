#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QFileDialog>
#include <QImage>
#include <QLabel>
#include <QTime>
#include <QMessageBox>
#include <QString>
#include <QGraphicsPixmapItem>
#include <QGraphicsScene>
#include <QProgressBar>
#include <QPixmap>
#include <QDebug>
#include <QTimer>
#include <opencv4/opencv2/opencv.hpp>

QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    MainWindow(QWidget *parent = nullptr);
    ~MainWindow();

private slots:
    void on_actionOpen_triggered();

    void on_actionSave_triggered();

    void on_actionShutdown_triggered();

    void on_timerTimeout();


    void on_actionShowpic_triggered();

//    void on_displayBrightness_clicked();

    void on_displayContrast_clicked();

private:
    Ui::MainWindow *ui;

    QString wybranyObraz;

    QImage *image;
    QGraphicsScene *scene;
    QPixmap pixmap;
    QGraphicsPixmapItem *graphicsPixmapItem;
    QLabel info, time_info;
    QProgressBar progressBar;
    QTimer *timer;
    QTime time;

};
#endif // MAINWINDOW_H
