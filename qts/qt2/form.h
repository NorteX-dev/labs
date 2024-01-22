#ifndef FORM_H
#define FORM_H

#include <QWidget>
#include <QLabel>
#include <QScrollArea>
#include <QGridLayout>

namespace Ui {
class Form;
}

class Form : public QWidget
{
    Q_OBJECT

public:
    explicit Form(QWidget *parent = nullptr);
    ~Form();
    void setObraz(QPixmap newPixMap);

private:
    Ui::Form *ui;
    QLabel *obraz;
    QScrollArea *obszar;
    QGridLayout *uklad;
};

#endif // FORM_H
