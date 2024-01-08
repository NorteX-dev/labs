#include "form.h"
#include "ui_form.h"

Form::Form(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::Form)
{
    ui->setupUi(this);

    // Stworzenie elementu Label który będzie zawierał obraz
    obraz = new QLabel();
    obraz->setText("some text");
    obraz->setMinimumSize(600, 500);
    obraz->setScaledContents(true);
    obraz->setBackgroundRole(QPalette::Base);

    // Stworzenie elementu ScrollArea aby umożliwić przewijanie zdjęcia
    obszar = new QScrollArea();
    obszar->setBackgroundRole(QPalette::Dark);
    obszar->setWidget(obraz);

    // Stworzenie elementu GridLayout który określa rozkład siatki
    uklad = new QGridLayout(this);
    uklad->addWidget(obszar);

    // Edycja tytułu i rozmiaru okienka
    this->setWindowTitle("Okienko obrazka");
    this->setMinimumSize(620, 520);
}

Form::~Form()
{
    delete ui;
}

void Form::setObraz(QPixmap newPixmap) {
    obraz->setPixmap(newPixmap);
}
