#include "bindialog.h"
#include "ui_bindialog.h"

BinDialog::BinDialog(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::BinDialog)
{
    ui->setupUi(this);
}

BinDialog::~BinDialog()
{
    delete ui;
}

void BinDialog::on_buttonBox_accepted()
{
    int thresholdR = ui->threshR->value();
    int thresholdG = ui->threshG->value();
    int thresholdB = ui->threshB->value();

    char mode;
    if(ui->binaryBtn->isChecked()) {
        mode = 'b';
    } else if (ui->invertBinaryBtn->isChecked()) {
        mode = 'i';
    } else if (ui->threshTo0Btn->isChecked()) {
        mode = '0';
    } else if(ui->threshToInvBtn->isChecked()) {
        mode = 's';
    } else if(ui->threshTruncBtn->isChecked()) {
        mode = 't';
    }

    show_thresholding(thresholdR, thresholdG, thresholdB, mode);
}


//if(!wybranyObraz.isEmpty()) {
        // Załadowanie zdjęcia
        // cv::Mat obrazek_wejsciowy = cv::imread(wybranyObraz.toStdString().c_str());
    // } else {
        
    // }
    
    // switch (mode) {
    // case 'b':
        
    //     break;
    // }
