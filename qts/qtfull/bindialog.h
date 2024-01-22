#ifndef BINDIALOG_H
#define BINDIALOG_H

#include <QDialog>

namespace Ui {
class BinDialog;
}

class BinDialog : public QDialog
{
    Q_OBJECT

public:
    explicit BinDialog(QWidget *parent = nullptr);
    ~BinDialog();

private slots:
    void on_buttonBox_accepted();

private:
    Ui::BinDialog *ui;
};

#endif // BINDIALOG_H
