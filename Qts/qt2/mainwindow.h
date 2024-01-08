#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QLabel>
#include <QImage>
#include <QFile>
#include "form.h"

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

    void on_pushButton_clicked();

    void on_actionokno_triggered();

private:
    Ui::MainWindow *ui;
    QLabel info;
    QImage plik_obrazek;
    Form okno;
};
#endif // MAINWINDOW_H
