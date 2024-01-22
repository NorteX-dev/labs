#include "mainwindow.h"
#include "./ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);

    info.setMinimumWidth(450);
    info.setFrameStyle(QFrame::WinPanel);
    info.setText("info text");

    time_info.setMinimumWidth(100);
    time_info.setFrameStyle(QFrame::Raised | QFrame::Box);
    time_info.setAlignment(Qt::AlignCenter);
    time_info.setText(time.currentTime().toString("HH:mm:ss"));

    ui->statusbar->addPermanentWidget(&time_info, 1);
    ui->statusbar->addPermanentWidget(&info, 2);

    timer = new QTimer(this);
    timer->start(1000);
    connect(timer, SIGNAL(timeout()), this, SLOT(on_timerTimeout()));

    timer2 = new QTimer(this);
    timer2->start(10000);
    connect(timer2, SIGNAL(timeout()), this, SLOT(on_timer2Timeout()));
}

MainWindow::~MainWindow()
{
    timer->stop();
    delete timer;
    delete ui;
}
void MainWindow::on_actionkoniec_triggered() {
    QApplication::quit();
}
void MainWindow::on_timerTimeout() {
    time_info.setText(time.currentTime().toString("HH:mm:ss"));
}
void MainWindow::on_timer2Timeout() {
    ui->textBrowser->append("Minęło 10 sekund...");
}

