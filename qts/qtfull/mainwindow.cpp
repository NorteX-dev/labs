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

    wielkosc_okna = 0;
}

MainWindow::~MainWindow()
{
    delete ui;
    delete timer;
}

void MainWindow::on_timerTimeout() {
    time_info.setText(time.currentTime().toString("HH:mm:ss"));
}


/*
 * PODSTAWOWE FUNKCJE PROGRAMU
 * */
// Przycisk Open na toolbarze
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

// Przycisk Save na toolbarze
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

// Przycisk Shutdown na toolbarze
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

/*
 * LAB 6 - Podział na kanały RGB
 * */
// Opcja rozdzielenia na kanały RGB

void MainWindow::on_actionSplitRgb_triggered()
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

/*
 * LAB 7 - Jasność i kontrast
 * */
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

/*
 * LAB 8 - Progowanie
 * */
static void Prog_callback(int v, void* p)
{
    MainWindow *img = (MainWindow*) p;
    img->Progowanie();
}
void MainWindow::Progowanie() {
    cv::threshold(zrodlo, wynik, prog, 255, typ);
    cv::imshow("Progowanie", wynik);
}
void MainWindow::on_actionProgowanie_triggered()
{
    char trackbar_type[] = "Rodzaj progowania: \n 0: Binary \n 1: Binary Inverted \n 2: Truncate \n 3: To Zero \n 4: To Zero Inverted";
    if(!wybranyObraz.isEmpty()) {
        cv::Mat obrazek_wejsciowy = cv::imread(wybranyObraz.toStdString().c_str(), cv::IMREAD_COLOR);
        cv::cvtColor( obrazek_wejsciowy, zrodlo, cv::COLOR_BGR2GRAY );
        cv::namedWindow("Progowanie", cv::WINDOW_AUTOSIZE);

        cv::createTrackbar(trackbar_type, "Progowanie", &typ, 4, &Prog_callback, this);
        cv::createTrackbar("Wartość progu", "Progowanie", &prog, 255, &Prog_callback, this);

        Progowanie();
    } else {
        QMessageBox(QMessageBox::Information, "Informacja",
                    "Nie załadowano pliku obrazu! Nie ma co pokazać.", QMessageBox::Ok).exec();
    }
}

/*
 * LAB 9 - Obrót
 * */
static void Rotacja_callback(int v, void* p)
{
    MainWindow *img = (MainWindow*) p;
    img->Rotacja();
}

void MainWindow::Rotacja()
{
    double angle = kat_obrotu;
    cv::Size rozmiar;
    rozmiar.width=zrodlo.cols;
    rozmiar.height=zrodlo.rows;
    cv::Point2f punkt_obrotu(rozmiar.width/2., rozmiar.height/2.);
    cv::Mat macierz_obrotu = cv::getRotationMatrix2D(punkt_obrotu, angle, 1.0);
    /* wywołujemy funkcje obrotu i aktualizujemy wynik operacji */
    cv::warpAffine(zrodlo, wynik, macierz_obrotu, rozmiar);
    cv::imshow("Rotacja", wynik );
}

void MainWindow::on_actionRotate_triggered()
{
    if(!wybranyObraz.isEmpty()) {
        cv::Mat obrazek_wejsciowy=cv::imread(wybranyObraz.toStdString().c_str(),cv::IMREAD_COLOR);
        zrodlo = obrazek_wejsciowy;
        cv::namedWindow("Rotacja",cv::WINDOW_AUTOSIZE);
        kat_obrotu=0; // startową wartość kata obrotu ustawiamy na 0
        cv::createTrackbar("Kąt obrotu","Rotacja",&kat_obrotu,360, &Rotacja_callback,this);
        Rotacja();
    } else {
        QMessageBox(QMessageBox::Information, "Informacja",
                    "Nie załadowano pliku obrazu! Nie ma co pokazać.", QMessageBox::Ok).exec();
    }
}

/*
 * LAB 9 - Skalowanie
 * */
static void Skaluj_callback(int v, void* p)
{
    MainWindow *img = (MainWindow*) p;
    img->Skaluj();
}

void MainWindow::Skaluj()
{
    /* tutaj przygotowujemy zmienne skali i rozmiaru */
    double wspolczynnik=wsp_skali+1; // zabezpieczenie przed wprowadzeniem wartości = 0
    wspolczynnik/=100; // zmiana zakresu wspolczynnika z [0..200] na [0.01 do 2.00]
    double Skala_x=wspolczynnik; // zmiana wymiaru w osi X
    double Skala_y=wspolczynnik; // zmiana wymiaru w osi Y
    cv::Size rozmiar;
    /* wywołujemy funkcje zmiany rozmiaru i aktualizujemy wynik operacji */
    cv::resize(zrodlo, wynik, rozmiar, Skala_x, Skala_y, cv::INTER_LINEAR);
    cv::imshow("Skalowanie", wynik);
}


void MainWindow::on_actionSkala_triggered()
{
    if(!wybranyObraz.isEmpty()) {
        cv::Mat obrazek_wejsciowy=cv::imread(wybranyObraz.toStdString().c_str(),cv::IMREAD_COLOR);
        zrodlo = obrazek_wejsciowy;
        cv::namedWindow("Skalowanie", cv::WINDOW_AUTOSIZE);
        cv::resizeWindow("Skalowanie", zrodlo.cols * 2, zrodlo.rows * 2);
        wsp_skali=100; // startową wartość współczynnika skali ustawiamy na 100%
        cv::createTrackbar("Współczynnik Skali", "Skalowanie", &wsp_skali, 200, &Skaluj_callback, this);
        Skaluj();
    } else {
        QMessageBox(QMessageBox::Information, "Informacja",
                    "Nie załadowano pliku obrazu! Nie ma co pokazać.", QMessageBox::Ok).exec();
    }
}

/*
 * LAB 10 - Uśrednianie
 * */
// Metoda callback która będzie wywoływana po zmianie wartości suwaka.
static void Usrednianie_callback(int value, void* pointer) {
    MainWindow *img = (MainWindow*) pointer;
    img->UpdateAveraging();
}
// Metoda aktualizująca uśrednienie.
void MainWindow::UpdateAveraging()
{
    int wymiar_okna=(2 * wielkosc_okna) + 1;
    cv::blur(zrodlo, wynik, cv::Size(wymiar_okna, wymiar_okna));
    cv::imshow("usrednianie", wynik);
}
// Metoda po kliknięciu opcji uśredniania.
void MainWindow::on_actionAveraging_triggered()
{
    if(!wybranyObraz.isEmpty()) {
        cv::Mat obrazek_wejsciowy = cv::imread(wybranyObraz.toStdString().c_str(),cv::IMREAD_COLOR);
        zrodlo = obrazek_wejsciowy;
        cv::namedWindow("usrednianie", cv::WINDOW_AUTOSIZE);
        cv::resizeWindow("usrednianie", zrodlo.cols * 2, zrodlo.rows * 2);
        wielkosc_okna = 1;
        cv::createTrackbar("Usrednienie", "usrednianie", &wielkosc_okna, 11, &Usrednianie_callback, this);
        UpdateAveraging();
    } else {
        QMessageBox(QMessageBox::Information, "Informacja",
                    "Nie załadowano pliku obrazu! Nie ma co pokazać.", QMessageBox::Ok).exec();
    }
}

// -----------------------------------------

static void Gauss_callback(int value, void* pointer) {
    MainWindow *img = (MainWindow*) pointer;
    img->UpdateGaussian();
}

void MainWindow::UpdateGaussian()
{
    int wymiar_okna = (2 * wielkosc_okna) + 1;
    cv::GaussianBlur(zrodlo, wynik, cv::Size(wymiar_okna, wymiar_okna), 0, 0);
    cv::imshow("gauss", wynik);
}

void MainWindow::on_actionGaussian_triggered()
{
    if(!wybranyObraz.isEmpty()) {
        cv::Mat obrazek_wejsciowy = cv::imread(wybranyObraz.toStdString().c_str(),cv::IMREAD_COLOR);
        zrodlo = obrazek_wejsciowy;
        cv::namedWindow("gauss", cv::WINDOW_AUTOSIZE);
        cv::resizeWindow("gauss", zrodlo.cols * 2, zrodlo.rows * 2);
        wielkosc_okna = 1;
        cv::createTrackbar("Rozmycie Gaussowskie", "gauss", &wielkosc_okna, 11, &Gauss_callback, this);
        UpdateGaussian();
    } else {
        QMessageBox(QMessageBox::Information, "Informacja",
                    "Nie załadowano pliku obrazu! Nie ma co pokazać.", QMessageBox::Ok).exec();
    }
}


// -----------------------------------------
void MainWindow::UpdateMediana()
{
    int wymiar_okna=(2 * wielkosc_okna) + 1;
    cv::medianBlur(zrodlo, wynik, wymiar_okna);
    cv::imshow("mediana", wynik);
}
static void Mediana_callback(int value, void* pointer) {
    MainWindow *img = (MainWindow*) pointer;
    img->UpdateMediana();
}
void MainWindow::on_actionMedianAvg_triggered()
{
    if(!wybranyObraz.isEmpty()) {
        cv::Mat obrazek_wejsciowy = cv::imread(wybranyObraz.toStdString().c_str(),cv::IMREAD_COLOR);
        zrodlo = obrazek_wejsciowy;
        cv::namedWindow("mediana", cv::WINDOW_AUTOSIZE);
        cv::resizeWindow("mediana", zrodlo.cols * 2, zrodlo.rows * 2);
        wielkosc_okna = 1;
        cv::createTrackbar("Uśrednienie medianowe", "mediana", &wielkosc_okna, 11, &Mediana_callback, this);
        UpdateMediana();
    } else {
        QMessageBox(QMessageBox::Information, "Informacja",
                    "Nie załadowano pliku obrazu! Nie ma co pokazać.", QMessageBox::Ok).exec();
    }
}


/*
 * LAB 11 - Detekcja krawędzi w obrazie RGB
 * */
// Metodą Sobela
void MainWindow::on_sobelClick_triggered()
{
    if(!wybranyObraz.isEmpty()) {
        cv::Mat obrazek_wejsciowy = cv::imread(wybranyObraz.toStdString().c_str(),cv::IMREAD_COLOR);
        cv::namedWindow("krsobel", cv::WINDOW_AUTOSIZE);
        cv::resizeWindow("krsobel", obrazek_wejsciowy.cols * 2, obrazek_wejsciowy.rows * 2);

        cv::Mat grad_x;
        cv::Mat grad_y;
        cv::Mat abs_grad_x;
        cv::Mat abs_grad_y;
        cv::Mat result;

        int scale = 1;
        int delta = 0;
        int ksize = 3;
        int ddepth = CV_16S;

        cv::Sobel(obrazek_wejsciowy, grad_x, ddepth, 1, 0, ksize, scale, delta, cv::BORDER_DEFAULT);
        cv::convertScaleAbs(grad_x, abs_grad_x);
        cv::Sobel(obrazek_wejsciowy, grad_y, ddepth, 0, 1, ksize, scale, delta, cv::BORDER_DEFAULT);
        cv::convertScaleAbs(grad_y, abs_grad_y);
        cv::addWeighted(abs_grad_x, 0.5, abs_grad_y, 0.5, 0, result);

        cv::imshow("krsobel", result);
    } else {
        QMessageBox(QMessageBox::Information, "Informacja",
                    "Nie załadowano pliku obrazu! Nie ma co pokazać.", QMessageBox::Ok).exec();
    }
}

// Metodą Prewitta
void MainWindow::on_prewittClick_triggered()
{
    // Kod do Prewitta i Canny'ego gdzieś zgubiłem i nie mogę odzyskać,
    // ale jest zawarty w pdfie lab 11.
}

// Metodą Canny'ego
void MainWindow::on_cannyClick_triggered()
{
    // Kod do Prewitta i Canny'ego gdzieś zgubiłem i nie mogę odzyskać,
    // ale jest zawarty w pdfie lab 11.
}

/*
 * LAB 12 - Operacje morfologiczne
 * */
void MainWindow::Dylatacja() {
    cv::Mat result;
    cv::Mat element;

    if(wybor_figury == 0) {
        element = cv::getStructuringElement(
            cv::MORPH_RECT,
            cv::Size(2 * rozmiar_bazowy_elementu + 1, 2 * rozmiar_bazowy_elementu + 1),
            cv::Point(rozmiar_bazowy_elementu, rozmiar_bazowy_elementu)
        );
    } else if(wybor_figury == 1) {
        element = cv::getStructuringElement(
            cv::MORPH_CROSS,
            cv::Size(2 * rozmiar_bazowy_elementu + 1, 2 * rozmiar_bazowy_elementu + 1),
            cv::Point(rozmiar_bazowy_elementu, rozmiar_bazowy_elementu)
        );
    } else if(wybor_figury == 2) {
        element = cv::getStructuringElement(
            cv::MORPH_ELLIPSE,
            cv::Size(2 * rozmiar_bazowy_elementu + 1, 2 * rozmiar_bazowy_elementu + 1),
            cv::Point(rozmiar_bazowy_elementu, rozmiar_bazowy_elementu)
        );
    }

    cv::dilate(zrodlo, result, element, cv::Point(rozmiar_bazowy_elementu, rozmiar_bazowy_elementu), 1, cv::BORDER_CONSTANT);
    cv::imshow("dylatacja", result);
}
static void Dylatacja_callback(int value, void* pointer) {
    MainWindow *img = (MainWindow*) pointer;
    img->Dylatacja();
}
void MainWindow::on_actionDylatacja_triggered()
{
    if(wybranyObraz.isEmpty()) {
        QMessageBox(QMessageBox::Information, "Informacja",
                    "Nie załadowano pliku obrazu! Nie ma co pokazać.", QMessageBox::Ok).exec();
        return;
    }
    cv::Mat obrazek_wejsciowy = cv::imread(wybranyObraz.toStdString().c_str(),cv::IMREAD_COLOR);
    zrodlo = obrazek_wejsciowy;
    cv::namedWindow("dylatacja", cv::WINDOW_AUTOSIZE);
    cv::createTrackbar("Wielkość bazowa", "dylatacja", &rozmiar_bazowy_elementu, 11, &Dylatacja_callback, this);
    cv::createTrackbar("Rodzaj elementu strukturalnego", "dylatacja", &wybor_figury, 3, &Dylatacja_callback, this);

    Dylatacja();
}

void MainWindow::Erozja() {
    cv::Mat result;
    cv::Mat element;

    if(wybor_figury == 0) {
        element = cv::getStructuringElement(
            cv::MORPH_RECT,
            cv::Size(2 * rozmiar_bazowy_elementu + 1, 2 * rozmiar_bazowy_elementu + 1),
            cv::Point(rozmiar_bazowy_elementu, rozmiar_bazowy_elementu)
        );
    } else if(wybor_figury == 1) {
        element = cv::getStructuringElement(
            cv::MORPH_CROSS,
            cv::Size(2 * rozmiar_bazowy_elementu + 1, 2 * rozmiar_bazowy_elementu + 1),
            cv::Point(rozmiar_bazowy_elementu, rozmiar_bazowy_elementu)
        );
    } else if(wybor_figury == 2) {
        element = cv::getStructuringElement(
            cv::MORPH_ELLIPSE,
            cv::Size(2 * rozmiar_bazowy_elementu + 1, 2 * rozmiar_bazowy_elementu + 1),
            cv::Point(rozmiar_bazowy_elementu, rozmiar_bazowy_elementu)
        );
    }

    cv::erode(zrodlo, result, element, cv::Point(rozmiar_bazowy_elementu, rozmiar_bazowy_elementu), 1, cv::BORDER_CONSTANT);
    cv::imshow("erozja", result);
}
static void Erozja_callback(int value, void* pointer) {
    MainWindow *img = (MainWindow*) pointer;
    img->Erozja();
}
void MainWindow::on_actionErozja_triggered()
{
    if(wybranyObraz.isEmpty()) {
        QMessageBox(QMessageBox::Information, "Informacja",
                    "Nie załadowano pliku obrazu! Nie ma co pokazać.", QMessageBox::Ok).exec();
        return;
    }
    cv::Mat obrazek_wejsciowy = cv::imread(wybranyObraz.toStdString().c_str(),cv::IMREAD_COLOR);
    zrodlo = obrazek_wejsciowy;
    cv::namedWindow("erozja", cv::WINDOW_AUTOSIZE);
    cv::createTrackbar("Wielkość bazowa", "erozja", &rozmiar_bazowy_elementu, 11, &Erozja_callback, this);
    cv::createTrackbar("Rodzaj elementu strukturalnego", "erozja", &wybor_figury, 3, &Erozja_callback, this);

    Erozja();
}

void MainWindow::Otwarcie() {
    cv::Mat result;
    cv::Mat element;

    if(wybor_figury == 0) {
        element = cv::getStructuringElement(
            cv::MORPH_RECT,
            cv::Size(2 * rozmiar_bazowy_elementu + 1, 2 * rozmiar_bazowy_elementu + 1),
            cv::Point(rozmiar_bazowy_elementu, rozmiar_bazowy_elementu)
        );
    } else if(wybor_figury == 1) {
        element = cv::getStructuringElement(
            cv::MORPH_CROSS,
            cv::Size(2 * rozmiar_bazowy_elementu + 1, 2 * rozmiar_bazowy_elementu + 1),
            cv::Point(rozmiar_bazowy_elementu, rozmiar_bazowy_elementu)
        );
    } else if(wybor_figury == 2) {
        element = cv::getStructuringElement(
            cv::MORPH_ELLIPSE,
            cv::Size(2 * rozmiar_bazowy_elementu + 1, 2 * rozmiar_bazowy_elementu + 1),
            cv::Point(rozmiar_bazowy_elementu, rozmiar_bazowy_elementu)
        );
    }

    cv::dilate(zrodlo, result, element, cv::Point(rozmiar_bazowy_elementu, rozmiar_bazowy_elementu), 1, cv::BORDER_CONSTANT);
    cv::erode(zrodlo, result, element, cv::Point(rozmiar_bazowy_elementu, rozmiar_bazowy_elementu), 1, cv::BORDER_CONSTANT);
    cv::imshow("otwarcie", result);
}
static void Otwarcie_callback(int value, void* pointer) {
    MainWindow *img = (MainWindow*) pointer;
    img->Otwarcie();
}
void MainWindow::on_actionOtwarcie_triggered()
{
    if(wybranyObraz.isEmpty()) {
        QMessageBox(QMessageBox::Information, "Informacja",
                    "Nie załadowano pliku obrazu! Nie ma co pokazać.", QMessageBox::Ok).exec();
        return;
    }
    cv::Mat obrazek_wejsciowy = cv::imread(wybranyObraz.toStdString().c_str(), cv::IMREAD_COLOR);
    zrodlo = obrazek_wejsciowy;
    cv::namedWindow("otwarcie", cv::WINDOW_AUTOSIZE);
    cv::createTrackbar("Wielkość bazowa", "otwarcie", &rozmiar_bazowy_elementu, 11, &Otwarcie_callback, this);
    cv::createTrackbar("Rodzaj elementu strukturalnego", "otwarcie", &wybor_figury, 3, &Otwarcie_callback, this);

    Otwarcie();
}


void MainWindow::Zamkniecie() {
    cv::Mat result;
    cv::Mat element;

    if(wybor_figury == 0) {
        element = cv::getStructuringElement(
            cv::MORPH_RECT,
            cv::Size(2 * rozmiar_bazowy_elementu + 1, 2 * rozmiar_bazowy_elementu + 1),
            cv::Point(rozmiar_bazowy_elementu, rozmiar_bazowy_elementu)
        );
    } else if(wybor_figury == 1) {
        element = cv::getStructuringElement(
            cv::MORPH_CROSS,
            cv::Size(2 * rozmiar_bazowy_elementu + 1, 2 * rozmiar_bazowy_elementu + 1),
            cv::Point(rozmiar_bazowy_elementu, rozmiar_bazowy_elementu)
        );
    } else if(wybor_figury == 2) {
        element = cv::getStructuringElement(
            cv::MORPH_ELLIPSE,
            cv::Size(2 * rozmiar_bazowy_elementu + 1, 2 * rozmiar_bazowy_elementu + 1),
            cv::Point(rozmiar_bazowy_elementu, rozmiar_bazowy_elementu)
        );
    }

    cv::erode(zrodlo, result, element, cv::Point(rozmiar_bazowy_elementu, rozmiar_bazowy_elementu), 1, cv::BORDER_CONSTANT);
    cv::dilate(zrodlo, result, element, cv::Point(rozmiar_bazowy_elementu, rozmiar_bazowy_elementu), 1, cv::BORDER_CONSTANT);
    cv::imshow("zamkniecie", result);
}
static void Zamkniecie_callback(int value, void* pointer) {
    MainWindow *img = (MainWindow*) pointer;
    img->Zamkniecie();
}
void MainWindow::on_actionZamkniecie_triggered()
{
    if(wybranyObraz.isEmpty()) {
        QMessageBox(QMessageBox::Information, "Informacja",
                    "Nie załadowano pliku obrazu! Nie ma co pokazać.", QMessageBox::Ok).exec();
        return;
    }
    cv::Mat obrazek_wejsciowy = cv::imread(wybranyObraz.toStdString().c_str(), cv::IMREAD_COLOR);
    zrodlo = obrazek_wejsciowy;
    cv::namedWindow("zamkniecie", cv::WINDOW_AUTOSIZE);
    cv::createTrackbar("Wielkość bazowa", "zamkniecie", &rozmiar_bazowy_elementu, 11, &Zamkniecie_callback, this);
    cv::createTrackbar("Rodzaj elementu strukturalnego", "zamkniecie", &wybor_figury, 3, &Zamkniecie_callback, this);

    Zamkniecie();
}
