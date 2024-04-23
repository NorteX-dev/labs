using lab4;

var osoba = new Osoba("Jan", "Kowalski", 1988, Plec.Mezczyzna);
Console.WriteLine("Utworzyłeś osobę: {0}", osoba.ZwrocInformacje());

var student = new Student("Tomasz", "Nowak", 1989, Plec.Mezczyzna, 1234);
Console.WriteLine("Utworzyłeś studenta: {0}", student.ZwrocInformacje());

var stypendysta = new Stypendysta("Joanna", "Zielińska", 1987, Plec.Kobieta, 1235, 500);
Console.WriteLine("Utworzyłeś stypendystę: {0}", stypendysta.ZwrocInformacje());

var wykladowca = new Wykladowca("Maria", "Skłodowska-Curie", 1867, Plec.Kobieta, Tytuly.prof);
Console.WriteLine("Utworzyłeś wykładowcę: {0}", wykladowca.ZwrocInformacje());
Console.ReadKey();

Osoba o2 = student,
    o3 = stypendysta,
    o4 = wykladowca;

Console.WriteLine("Utworzyłeś osobę: {0}", o2.ZwrocInformacje());
Console.WriteLine("Utworzyłeś osobę: {0}", o3.ZwrocInformacje());
Console.WriteLine("Utworzyłeś osobę: {0}", o4.ZwrocInformacje());

Console.ReadKey();