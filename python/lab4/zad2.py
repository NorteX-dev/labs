class Vehicle:
    def get_sound(self):
        print("vehicle's brum brum")

    def get_owner(self):
        pass


class Car(Vehicle):
    def __init__(self, owner: str, table: str):
        self.owner = owner
        self.table = table

    def get_sound(self) -> None:
        print("car's brum brum")

    def get_owner(self) -> str:
        return self.owner


vehicle = Vehicle()
vehicle.get_sound()

car = Car("John", "ABC123")
car.get_sound()

print(car.get_owner())
print(vehicle.get_owner())
