using CSharpClientConsoleTestApp.SOAWebServiceReference;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSharpClientConsoleTestApp
{
    class Program
    {
        static void Main(string[] args)
        {
            while (true) // Loop indefinitely
            {
                try
                {
                    Employee emp = new Employee();
                    Console.WriteLine("Enter Employee Name:");
                    emp.name = Console.ReadLine();
                    Console.WriteLine("Enter Employee Gender(M/F):");
                    emp.gender = Convert.ToChar(Console.ReadLine());
                    Console.WriteLine("Enter Employee Age:");
                    emp.age = Convert.ToByte(Console.ReadLine());
                    Console.WriteLine("Enter Employee is Single (true/false):");
                    emp.isSingle = Convert.ToBoolean(Console.ReadLine());
                    Console.WriteLine("Enter Employee weight(Kg):");
                    emp.weight = Convert.ToInt32(Console.ReadLine());
                    Console.WriteLine("Enter Employee height(m):");
                    emp.height = Convert.ToDouble(Console.ReadLine());
                    Console.WriteLine("Enter Employee eye color:");
                    emp.eyeColor = Console.ReadLine();
                    Console.WriteLine("Enter Employee salary:");
                    emp.salary = (float)Convert.ToDouble(Console.ReadLine());
                    Console.WriteLine("Enter Employee Adress(street):");
                    emp.street = Console.ReadLine();
                    Console.WriteLine("Enter Employee Adress(house number):");
                    emp.houseNumber = Console.ReadLine();
                    Console.WriteLine("Enter Employee Adress(floor number):");
                    emp.floorNumber = Console.ReadLine();
                    Console.WriteLine("Enter Employee Adress(city):");
                    emp.city = Console.ReadLine();
                    Console.WriteLine("Enter Employee Adress(zip code):");
                    emp.zip = Console.ReadLine();
                    Console.WriteLine("Enter Employee Adress(country):");
                    emp.country = Console.ReadLine();
                     
                     
                    Console.WriteLine("Send TO Server (SOA:1 REST:2 SIL:3):");
                    string line  = Console.ReadLine();
                    Stopwatch stopWatch = new Stopwatch();
                    stopWatch.Start();
                    switch (line)
                    {
                        case "1":
                            //Call SOA
                               JavaWebServiceClient service = new JavaWebServiceClient();
                            // Get result from server
                               employee javaEmployee = new employee();
                               javaEmployee.name = emp.name;
                               javaEmployee.country = emp.country;
                               javaEmployee.age = (sbyte)emp.age;
                               javaEmployee.city = emp.city;
                               javaEmployee.country = emp.country;
                               javaEmployee.countryCode = emp.countryCode;
                               javaEmployee.eyeColor = emp.eyeColor;
                               javaEmployee.floorNumber = emp.floorNumber;
                               javaEmployee.gender = emp.gender;
                               javaEmployee.height = emp.height;
                               javaEmployee.houseNumber = emp.houseNumber;
                               javaEmployee.isSingle = emp.isSingle;
                               javaEmployee.name = emp.name;
                               javaEmployee.salary = emp.salary;
                               javaEmployee.street = emp.street;
                               javaEmployee.weight = emp.weight;
                               javaEmployee.zip = emp.zip;
                               Console.WriteLine((service.GetObjectSOA(javaEmployee)));
                            break;
                        case "2":
                            break;
                        case "3":
                            break;
                        default:
                            break;
                    }
                    stopWatch.Stop();
                    // Get the elapsed time as a TimeSpan value.
                    TimeSpan ts = stopWatch.Elapsed;

                    // Format and display the TimeSpan value.
                    string elapsedTime = String.Format("{0:00}:{1:00}:{2:00}.{3:00}",
                        ts.Hours, ts.Minutes, ts.Seconds,
                        ts.Milliseconds / 10);
                    Console.WriteLine("Calculation Time " + elapsedTime);

                    Console.WriteLine("Type exit for exit or new for new one ");
                    line = Console.ReadLine();
                    if (line == "exit") // Check string
                    {
                        break;
                    }
                    Console.Clear();

                }
                catch (Exception e)
                {
                    Console.Clear();
                    Console.WriteLine("OPPS!! You made a mistake: " + e.Message);
                    Console.WriteLine("Restarting Again");
                }
               
              
            }
        }
    }
}
