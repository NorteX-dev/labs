#include "mainwindow.h"
#include "./ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);

    info.setMinimumWidth(450);
    info.setText("empty text");

    progressBar.setValue(0);
    progressBar.setRange(0, 100);
    progressBar.setMinimumWidth(100);

    time_info.setMinimumWidth(50);
    time_info.setFrameStyle(QFrame::Raised | QFrame::Box);
    time_info.setAlignment(Qt::AlignCenter);
    time_info.setText(time.currentTime().toString("HH:mm:ss"));

    ui->statusbar->addPermanentWidget(&time_info, 1);
    ui->statusbar->addPermanentWidget(&info, 2);
    ui->statusbar->addPermanentWidget(&progressBar, 3);

    timer = new QTimer(this);
    timer->start(1000);
    connect(timer, SIGNAL(timeout()), this, SLOT(on_timerTimeout()));
}

MainWindow::~MainWindow()
{
    delete ui;
    delete timer;
}

void MainWindow::on_timerTimeout() {
    time_info.setText(time.currentTime().toString("HH:mm:ss"));
}

void MainWindow::on_actionOpen_triggered()
{
    progressBar.setValue(0);
    QString imagePath = QFileDialog::getOpenFileName(this, tr("Otwórz Plik"), "",
                                                     tr("JPEG(*.jpg *.jpeg);;PNG (*.png)"));
    progressBar.setValue(50);
    if (!imagePath.isEmpty()) {
        wybranyObraz = imagePath;
        image = new QImage();
        image->load(imagePath);
        pixmap = QPixmap::fromImage(*image);
        scene = new QGraphicsScene(this);
        graphicsPixmapItem = scene->addPixmap(pixmap);
        scene->setSceneRect(pixmap.rect());
        ui->graphicsView->setScene(scene);
        delete (image); // zabezpieczenie przed wyciekiem pamięci.
        info.setText("Wybrany Plik:" + imagePath + "rozmiar:" + QString::number(pixmap.width()) + "x" + QString::number(pixmap.height()));
    }
    progressBar.setValue(100);
}


void MainWindow::on_actionSave_triggered()
{
    progressBar.setValue(0);
    if(graphicsPixmapItem != NULL) {
        QString imagePath = QFileDialog::getSaveFileName(this, tr("Zapisz Plik"), "",
                                                         tr("JPEG (*.jpg *.jpeg);;PNG (*.png)"));
        progressBar.setValue(50);
        if (!imagePath.isEmpty())
        {
            QImage image = graphicsPixmapItem->pixmap().toImage();
            if (image.size().isValid() == true) image.save(imagePath);
        }
    } else {
        QMessageBox(QMessageBox::Information, "Informacja",
                    "Nie załadowano pliku obrazu! Nie ma co zapisać.", QMessageBox::Ok).exec();
    }
    progressBar.setValue(100);
}


void MainWindow::on_actionShutdown_triggered()
{
    QMessageBox MojeOkienkoWiadomosci(QMessageBox::Warning, "Wyjście z Programu",
                "Czy chcesz wyjść z programu?", QMessageBox::Yes | QMessageBox::No);
    MojeOkienkoWiadomosci.setButtonText(QMessageBox::Yes,"Tak");
    MojeOkienkoWiadomosci.setButtonText(QMessageBox::No,"Nie");
    if (MojeOkienkoWiadomosci.exec() == QMessageBox::Yes) {
        QApplication::quit();
    }
}


void MainWindow::on_actionShowpic_triggered()
{
    if(!wybranyObraz.isEmpty())
    {
        cv::Mat obrazek_wejsciowy = cv::imread(wybranyObraz.toStdString().c_str());

        cv::Mat skladowa_blue[3];
        cv::Mat skladowa_green[3];
        cv::Mat skladowa_red[3];
        cv::split(obrazek_wejsciowy, skladowa_blue);
        cv::split(obrazek_wejsciowy, skladowa_green);
        cv::split(obrazek_wejsciowy, skladowa_red);

        skladowa_blue[1] = cv::Mat::zeros(skladowa_blue[1].size(), CV_8UC1);
        skladowa_blue[2] = cv::Mat::zeros(skladowa_blue[2].size(), CV_8UC1);

        skladowa_green[0] = cv::Mat::zeros(skladowa_green[0].size(), CV_8UC1);
        skladowa_green[2] = cv::Mat::zeros(skladowa_green[2].size(), CV_8UC1);

        skladowa_red[0] = cv::Mat::zeros(skladowa_red[0].size(), CV_8UC1);
        skladowa_red[1] = cv::Mat::zeros(skladowa_red[1].size(), CV_8UC1);

        cv::Mat blue_channel;
        cv::Mat green_channel;
        cv::Mat red_channel;
        cv::merge(skladowa_blue, 3, blue_channel);
        cv::merge(skladowa_green, 3, green_channel);
        cv::merge(skladowa_red, 3, red_channel);

        cv::imshow("Blue", blue_channel);
        cv::imshow("Green", green_channel);
        cv::imshow("Red", red_channel);
    } else {
        QMessageBox(QMessageBox::Information, "Informacja",
                   "Nie załadowano pliku obrazu! Nie ma co pokazać.", QMessageBox::Ok).exec();
    }
}

void MainWindow::on_displayContrast_clicked()
{
    // Sprawdza czy jest wybrany obraz
    if(!wybranyObraz.isEmpty()) {
        // Załadowanie zdjęcia
        cv::Mat obrazek_wejsciowy = cv::imread(wybranyObraz.toStdString().c_str());

        // Pobranie wartości każdego z pasków
        double contrastFactorR = ui->contrastR->value() / 100.0;
        double contrastFactorG = ui->contrastG->value() / 100.0;
        double contrastFactorB = ui->contrastB->value() / 100.0;
        int brightnessFactor = ui->brightnessSlider->value() - 150;

        // Dla każdego x, y...
        for(int y = 0; y < obrazek_wejsciowy.rows; y++)
        {
            for(int x = 0; x < obrazek_wejsciowy.cols; x++)
            {
                // ...wykonuję saturate_cast, oraz podaję wymnożony kanał R G B
                // z dodaną jasnością brightnessFactor, która tyczy się wszystkich kanałów.
                obrazek_wejsciowy.at<cv::Vec3b>(y,x)[0] = cv::saturate_cast<uchar>(
                    contrastFactorB * obrazek_wejsciowy.at<cv::Vec3b>(y,x)[0] + brightnessFactor);
                obrazek_wejsciowy.at<cv::Vec3b>(y,x)[1] = cv::saturate_cast<uchar>(
                    contrastFactorG * obrazek_wejsciowy.at<cv::Vec3b>(y,x)[1] + brightnessFactor);
                obrazek_wejsciowy.at<cv::Vec3b>(y,x)[2] = cv::saturate_cast<uchar>(
                    contrastFactorR * obrazek_wejsciowy.at<cv::Vec3b>(y,x)[2] + brightnessFactor);
            }
        }

        // Wyświetl okno
        cv::imshow("Contrast and Brightness", obrazek_wejsciowy);
    } else {
        QMessageBox(QMessageBox::Information, "Informacja",
                   "Nie załadowano pliku obrazu! Nie ma co pokazać.", QMessageBox::Ok).exec();
    }
}

