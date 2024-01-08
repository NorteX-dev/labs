#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QLabel>
#include <QTimer>
#include <QTime>
#include <QUdpSocket>
#include <QByteArray>

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
    void on_actionKoniec_triggered();
    void on_zegarekTimeout();
    void on_PakietUDP();

    void on_pushButton_clicked();

private:
    Ui::MainWindow *ui;
    QLabel info, czas_info;
    QTimer *zegarek;
    QTime czas;
    QUdpSocket *gniazdo;
};
#endif // MAINWINDOW_H
