class car:
    def __init__(self, m):
        self.model = m
        self.wheels = 4

    def drive(self):
        if (self.wheels < 4):
            print(self.model + " no go vroom")
            return
        print(self.model + " goes vroom")

    def getNumwheels(self):
        return self.wheels
    
    def driveIntoDitch(self,wheelsLost):
        self.wheels -= wheelsLost
    
c1 = car("Civic Type R")
c2 = car("Toyota Camry")

c1.drive()
c1.driveIntoDitch(1)
c1.drive()

print(c2.getNumwheels())
