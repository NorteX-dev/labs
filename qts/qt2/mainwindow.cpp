#include "mainwindow.h"
#include "./ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);

    info.setText("pewien tekst c:");
    info.setMinimumWidth(450);
    info.setFrameStyle(QFrame::WinPanel);

    ui->statusbar->addPermanentWidget(&info);
}

MainWindow::~MainWindow()
{
    delete ui;
}


void MainWindow::on_actionkoniec_triggered()
{
    QApplication::quit();
}


void MainWindow::on_pushButton_clicked()
{
    if(!QFile::exists(ui->lineEdit->text())) {
        info.setText("Error: File path incorrect.");
        return;
    }
    info.setText("Reading file...");
    plik_obrazek.load(ui->lineEdit->text());
    ui->obraz->setPixmap(QPixmap::fromImage(plik_obrazek));
}


void MainWindow::on_actionokno_triggered()
{
    if(!QFile::exists(ui->lineEdit->text())) {
        info.setText("Error: File path incorrect.");
        return;
    }
    info.setText("Reading file...");
    plik_obrazek.load(ui->lineEdit->text());
    okno.setObraz(QPixmap::fromImage(plik_obrazek));
    okno.show();
}

