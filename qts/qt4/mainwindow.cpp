#include "mainwindow.h"
#include "./ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);

    info.setMinimumWidth(450);
//    info.setFrameRect(QFrame::WinPanel);
    info.setText("empty text");

    czas_info.setMinimumWidth(100);
    czas_info.setFrameStyle(QFrame::Raised | QFrame::Box);
    czas_info.setAlignment(Qt::AlignCenter);
    czas_info.setText(czas.currentTime().toString("HH:mm:ss"));

    ui->statusbar->addPermanentWidget(&czas_info, 1);
    ui->statusbar->addPermanentWidget(&info, 2);

    zegarek = new QTimer(this);
    zegarek->start(1000);
    connect(zegarek, SIGNAL(timeout()), this, SLOT(on_zegarekTimeout()));

    gniazdo = new QUdpSocket(this);
    gniazdo->bind(QHostAddress::AnyIPv4, 12345);
    connect(gniazdo, SIGNAL(readyRead()), this, SLOT(on_PakietUDP()));
}

MainWindow::~MainWindow()
{
    gniazdo->close();
    delete gniazdo;
    zegarek->stop();
    delete zegarek;
    delete ui;
}


void MainWindow::on_actionKoniec_triggered()
{
    QApplication::quit();
}


void MainWindow::on_zegarekTimeout() {

}

void MainWindow::on_pushButton_clicked()
{
    QByteArray data;
    data.append(ui->lineEdit->text());
//    gniazdo->writeDatagram(data, QHostAddress::LocalHost, 12345);
    gniazdo->writeDatagram(data, QHostAddress(ui->adres->text()), 12345);
    ui->lineEdit->clear();
}


void MainWindow::on_PakietUDP() {
    QByteArray dane_odebrane;
    QHostAddress adres_nadawcy;
    quint16 port_nadawcy;
    QString tekst_odebrany, status;
    dane_odebrane.resize(gniazdo->pendingDatagramSize());
    gniazdo->readDatagram(dane_odebrane.data(), dane_odebrane.size(),
                          &adres_nadawcy, &port_nadawcy);
    tekst_odebrany = QString::fromStdString(dane_odebrane.data());
    ui->textBrowser->append(tekst_odebrany);

    status.append("Mam pakiet: IP=" + adres_nadawcy.toString());
    status.append(" z portu=" + QString::number(port_nadawcy));
    status.append(" o wielkosci=" + QString::number(dane_odebrane.size()) + " bajtów");

    info.setText(status);
}
