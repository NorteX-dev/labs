from typing import List


class Student:
    quantity = 0

    def __init__(self):
        self.name = ""
        self.last_name = ""
        self.marks = []
        self.index_number = -1
        Student.quantity += 1

    def give_name(self, name: str, last_name: str) -> None:
        self.name = name
        self.last_name = last_name

    def give_mark(self, mark: int) -> None:
        self.marks.append(mark)

    def get_marks(self) -> List[int]:
        return self.marks

    def say_hello(self) -> None:
        print("Hello! I'm " + self.name + " " + self.last_name +
              ", my index number is " + str(self.index_number))

    def calculate_avg(self) -> int:
        sum = 0
        for mark in self.marks:
            sum += mark
        return sum / len(self.marks)

    def add_index_number(self, index_number: int) -> None:
        self.index_number = index_number


s = Student()
s.give_name("Jane", "Doe")
s.give_mark(5)  # wywołanie sposób 1
Student.give_mark(s, 3)  # wywołanie sposób 2
print(s.get_marks())
print("Średnia", s.calculate_avg())
s.add_index_number(123456)
print("Numer indeksu", s.index_number)
s.say_hello()
