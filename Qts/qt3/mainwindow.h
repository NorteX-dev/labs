#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QLabel>
#include <QTimer>
#include <QTime>

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
    void on_actionkoniec_triggered();
    void on_timerTimeout();
    void on_timer2Timeout();

private:
    Ui::MainWindow *ui;
    QLabel info;
    QLabel time_info;
    QTimer *timer, *timer2;
    QTime time;
};
#endif // MAINWINDOW_H
