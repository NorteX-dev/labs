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

    cv::Mat zrodlo;
    cv::Mat wynik;

    // Ref.: do lab 8
    int prog;
    int typ;

    // Lab 8
    void Progowanie();

    // Ref.: do lab 9
    int kat_obrotu = 0;
    int wsp_skali = 100;


    // Lab 9
    void Skaluj();
    void Rotacja();

    // Ref.: do lab 10
    int wielkosc_okna = 1;

    // Lab 10
    void UpdateAveraging();
    void UpdateGaussian();
    void UpdateMediana();

private slots:
    // Lab 5
    void on_actionOpen_triggered();

    void on_actionSave_triggered();

    void on_actionShutdown_triggered();

    void on_timerTimeout();

    // Lab 6
    void on_actionSplitRgb_triggered();

    // Lab 7
    void on_displayContrast_clicked();

    // Lab 8
    void on_actionProgowanie_triggered();

    // Lab 9
    void on_actionSkala_triggered();

    void on_actionRotate_triggered();

    // Lab 10
    void on_actionAveraging_triggered();

    void on_actionGaussian_triggered();

    void on_actionMedianAvg_triggered();


    // Lab 11
    void on_sobelClick_triggered();

    void on_prewittClick_triggered();

    void on_cannyClick_triggered();

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
