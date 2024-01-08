#include "mainwindow.h"
#include "./ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::on_horizontalSlider_valueChanged(int value)
{
    ui->progressBar->setValue(value);
}

void MainWindow::on_pushButton_clicked()
{
    ui->label->setText(ui->lineEdit->text());
}


void MainWindow::on_actionkoniec_triggered()
{
    QApplication::exit();
}

