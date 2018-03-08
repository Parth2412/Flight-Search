import java.util.*;
import java.io.*;

abstract class FlightSearch implements Serializable
{
	protected int refNo;
	protected String from;
	protected String to;
	protected String startMonth;
	protected String startTime;
	protected String journeyTime;
	protected String companyName;
	protected double price;
	FlightSearch(int refNoX)
	{
		refNo = refNoX;
		from = null;
		to = null;
		startMonth = null;
		startTime = null;
		journeyTime = null;
		companyName = null;
		price = 0;
	}
	public void setData(String fromX, String toX, String startMonthX, String startTimeX, String journeyTimeX, String companyNameX, double priceX)
	{
		from = fromX;
		to = toX;
		startMonth = startMonthX;
		startTime = startTimeX;
		journeyTime = journeyTimeX;
		companyName = companyNameX;
		price = priceX;
	}
	public void display()
	{
		System.out.println("From: " + from);
		System.out.println("To: " + to);
		System.out.println("Month: " + startMonth);
		System.out.println("Time: " + startTime);
		System.out.println("Journey Time: " + journeyTime);
		System.out.println("Company Name: " + companyName);
		System.out.println("Price: " + price);	
	}
	public boolean compare(String userNameInput, String passwordInputX, int refNoInput)
	{
		String refNoString = String.valueOf(refNoInput) + ".dat";
		try
		{
			DataInputStream d = new DataInputStream(new FileInputStream(refNoString));
			refNo = d.readInt();
			String userName = d.readUTF();
			String password = d.readUTF();
			if(userName.equals(userNameInput) && password.equals(passwordInputX))
				return true;
			else
				return false;
		}
		catch(Exception e)
		{
			//System.out.println("Error generated. Error Desc: " + e);
			return false;
		}
	}
}

class DirectUserSearch extends FlightSearch implements Serializable
{
	DirectUserSearch(int refNoX)
	{
		super(refNoX);
	}
	public void setData(String fromX, String toX, String startMonthX, String startTimeX, String journeyTimeX, String companyNameX, double priceX)
	{
		super.setData(fromX, toX, startMonthX, startTimeX, journeyTimeX, companyNameX, priceX);
	}
	public void display()
	{
		super.display();
	}
}

class BookTicketSearch extends FlightSearch implements Serializable
{
	private String userName;
	private String password;
	private String mobileNo;
	private boolean bookTicketChoice;
	private char gender;
	BookTicketSearch(int refNoX)
	{
		super(refNoX);
		userName = null;
		password = null;
		mobileNo = null;
		bookTicketChoice = false;
		gender = 'm';
	}
	public void setData(String userNameX, String passwordX, String mobileNoX, char genderX, String fromX, String toX, String startMonthX, String startTimeX, String journeyTimeX, String companyNameX, double priceX)
	{
		userName = userNameX;
		password = passwordX;
		mobileNo = mobileNoX;
		gender = genderX;
		super.setData(fromX, toX, startMonthX, startTimeX, journeyTimeX, companyNameX, priceX);
	}
	public void display()
	{
		System.out.println("Username: " + userName);
		System.out.println("Mobile No: " + mobileNo);
		System.out.println("Gender: " + gender);
		super.display();
	}
}

class FlightSearchFinal
{
	public static void displayList()
	{
		String []cities = {"Chennai", "Bangalore", "Delhi", "Ahmedabad", "Mumbai"};
		int i;
		System.out.print("\n");
		for(i=0; i<cities.length; i++)
		{
			System.out.println(i+1 + ". " + cities[i]);
		}
		System.out.print("\n");
	}
	public static void displayTime()
	{
		String []time = {"00:00", "4:00", "8:00", "12:00", "16:00", "20:00"};
		int i;
		System.out.print("\n");
		for(i=0; i<time.length; i++)
		{
			System.out.println(i+1 + ". " + time[i]);
		} 
		System.out.print("\n");
	}
	public static void displayMonth()
	{
		String []months = {"Jan", "Feb", "Mar", "Apr", "May"};
		int i;
		System.out.print("\n");
		for(i=0; i<months.length; i++)
		{
			System.out.println(i+1 + ". " + months[i]);
		}
		System.out.print("\n");
	}
	public static void main(String args[])
	{
		Scanner input = new Scanner(System.in);
		byte choice, choiceX=0, choiceY, choiceMonth, choiceTime, choiceAirway, choiceInput;
		String fromY=null, toY=null, mobileNoY=null, userNameY=null, passwordY=null, startMonthY=null, startTimeY=null;
		boolean bookTicketChoiceY;
		char genderY=0, bookTicketChar;
		boolean bookTicketBoolean;
		int refNoY = 0, flag = 1;
		String []time = {"00:00", "4:00", "8:00", "12:00", "16:00", "20:00", "24:00"};
		do
		{
			System.out.print("\n0. Terminate \n1. User side \n2. Administrator side \n\n");
			System.out.print("______________________________________________________\n");
			System.out.print("\nEnter your choice: ");
			choiceInput = input.nextByte();
			if(choiceInput == 1)
			{	
				System.out.print("______________________________________________________\n");
				System.out.print("\n\t0. Terminate \n\t1. Direct Search without login \n\t2. Book ticket with search \n\t3. View Direct User Search History \n\t4. View Booked ticket History \n\n");
				System.out.print("______________________________________________________\n");
				System.out.print("\n\tEnter your choice: ");
				choice = input.nextByte();
				refNoY++;
				FlightSearch f = new DirectUserSearch(refNoY);
				if(choice == 1)
				{
					f = new DirectUserSearch(refNoY);
				}
				if(choice == 2)
				{
					f = new BookTicketSearch(refNoY);
					System.out.print("______________________________________________________\n");
					System.out.print("\n\t\tEnter UserName: ");
					userNameY = input.next();
					System.out.print("\t\tEnter Password: ");
					passwordY = input.next();
					System.out.print("\t\tMobile No: ");
					mobileNoY = input.next();
					System.out.print("\t\tGender: ");
					genderY = input.next().charAt(0);
				}
				if(choice!=0 && choice!=3 && choice!=4)
				{
					System.out.print("\nYour reference no is " + refNoY + "\n");
					System.out.println("From: ");
					displayList();
					System.out.print("Enter your choice: ");
					choiceX = input.nextByte();
					switch(choiceX)
					{
						case 1:
						fromY = "Chennai";
						for(; ; )
						{
							flag=1;
							System.out.println("To: ");
							displayList();
							System.out.print("Enter your choice: ");
							choiceY = input.nextByte();
							switch(choiceY)
							{
								case 1:
								System.out.println("Source and destination place cannot be same.");
								flag=0;
								break;
								case 2:
								toY = "Bangalore";
								break;
								case 3:
								toY = "Delhi";
								break;
								case 4:
								toY = "Ahmedabad";
								break;
								case 5:
								toY = "Mumbai";
								break;
							}
							if(flag == 1)
							{
								break;
							}
						}
						break;
						case 2:
						fromY = "Bangalore";
						for(; ; )
						{	
							flag=1;		
							System.out.println("To: ");
							displayList();
							System.out.print("Enter your choice: ");
							choiceY = input.nextByte();
							switch(choiceY)
							{
								case 1:
								toY = "Chennai";
								break;
								case 2:
								System.out.println("Source and destination place cannot be same.");
								flag = 0;
								break;
								case 3:
								toY = "Delhi";
								break;
								case 4:
								toY = "Ahmedabad";
								break;
								case 5:
								toY = "Mumbai";
								break;
							}
							if(flag == 1)
							{
								break;
							}
						}
						break;
						case 3:
						fromY = "Delhi";
						for(; ; )
						{	
							flag=1;
							System.out.println("To: ");
							displayList();
							System.out.print("Enter your choice: ");
							choiceY = input.nextByte();
							switch(choiceY)
							{
								case 1:
								toY = "Chennai";
								break;
								case 2:
								toY = "Bangalore";
								break;
								case 3:
								System.out.println("Source and destination place cannot be same.");
								flag = 0;
								break;
								case 4:
								toY = "Ahmedabad";
								break;
								case 5:
								toY = "Mumbai";
								break;
							}
							if(flag == 1)
							{
								break;
							}
						}
						break;
						case 4:
						fromY = "Ahmedabad";
						for(; ; )
						{
							flag=1;
							System.out.println("To: ");
							displayList();
							System.out.print("Enter your choice: ");
							choiceY = input.nextByte();
							switch(choiceY)
							{
								case 1:
								toY = "Chennai";
								break;
								case 2:
								toY = "Bangalore";
								break;
								case 3:
								toY = "Delhi";
								break;
								case 4:
								System.out.println("Source and destination place cannot be same.");
								flag = 0;
								break;
								case 5:
								toY = "Mumbai";
								break;
							}
							if(flag == 1)
							{
								break;
							}
						}
						break;
						case 5:
						fromY = "Mumbai";
						for(; ; )
						{
							flag=1;
							System.out.println("To: ");
							displayList();
							System.out.print("Enter your choice: ");
							choiceY = input.nextByte();
							switch(choiceY)
							{
								case 1:
								toY = "Chennai";
								break;
								case 2:
								toY = "Bangalore";
								break;
								case 3:
								toY = "Delhi";
								break;
								case 4:
								toY = "Ahmedabad";
								break;
								case 5:
								System.out.println("Source and destination place cannot be same.");
								flag = 0;
								break;
							}
							if(flag == 1)
							{
								break;
							}
						}
						break;
					}
					System.out.println("\nCurrent date and time: ");
					Date date = new Date();
					System.out.println(date);
					System.out.println("\nEnter your journey month from the selected months: ");
					displayMonth();
					System.out.print("Enter your choice: ");
					choiceMonth = input.nextByte();
					switch(choiceMonth)
					{
						case 1:
						startMonthY = "Jan";
						break;
						case 2:
						startMonthY = "Feb";
						break;
						case 3:
						startMonthY = "Mar";
						break;
						case 4:
						startMonthY = "Apr";
						break;
						case 5:
						startMonthY = "May";
						break;
					}
					System.out.println("Enter your journey time from the selected times: ");
					displayTime();
					System.out.print("Enter your choice: ");
					choiceTime = input.nextByte();
					switch(choiceTime)
					{
						case 1:
						startTimeY = "00:00";
						break;
						case 2:
						startTimeY = "04:00";
						break;
						case 3:
						startTimeY = "08:00";
						break;
						case 4:
						startTimeY = "12:00";
						break;
						case 5:
						startTimeY = "16:00";
						break;
						case 6:
						startTimeY = "20:00";
						break;
					}
					if(fromY.equals("Chennai") && toY.equals("Bangalore") && (startTimeY.equals("00:00") || startTimeY.equals("04:00")))
					{
						System.out.println("No of flights available: 2");
						System.out.println("\n1. Name: Chennai Airways  Price: 3500  Journey hours: 1:00");
						System.out.println("2. Name: Java Airways  Price: 3000  Journey hours: 1:15");
						System.out.print("\nWhich flight would you prefer? ");
						choiceAirway = input.nextByte();
						switch(choiceAirway)
						{
							case 1:
							if(choice == 2)
							{
								System.out.print("Do you want to book ticket(y/n)? ");
								bookTicketChar = input.next().charAt(0);
								if(bookTicketChar == 'y')
								{
									bookTicketBoolean = true;
									System.out.println("Ticket Successfully booked!!");
								}
								else
									bookTicketBoolean = false;
							}
							else
							{
								System.out.println("Flight stored in history.");
								bookTicketBoolean = false;
							}
							if(choice == 2)
							{
								((BookTicketSearch)f).setData(userNameY, passwordY, mobileNoY, genderY, fromY, toY, startMonthY, startTimeY, "1:00", "Chennai Airways", 3500);
							}
							else
							{
								((DirectUserSearch)f).setData(fromY, toY, startMonthY, startTimeY, "1:00", "Chennai Airways", 3500);
							}
							break;
							case 2:
							if(choice == 2)
							{
								System.out.print("Do you want to book ticket(y/n)? ");
								bookTicketChar = input.next().charAt(0);
								if(bookTicketChar == 'y')
								{
									bookTicketBoolean = true;
									System.out.println("Ticket Successfully booked!!");
								}
								else
								{
									System.out.println("Flight stored in history.");
									bookTicketBoolean = false;
								}
							}
							else
								bookTicketBoolean = false;
							if(choice == 2)
							{
								((BookTicketSearch)f).setData(userNameY, passwordY, mobileNoY, genderY, fromY, toY, startMonthY, startTimeY, "1:15", "Java Airways", 3000);
							}
							else
							{
								((DirectUserSearch)f).setData(fromY, toY, startMonthY, startTimeY, "1:15", "Java Airways", 3000);
							}
							break;
						}
					}
					else if(fromY.equals("Chennai") && toY.equals("Delhi") && (startTimeY.equals("00:00") || startTimeY.equals("04:00") || startTimeY.equals("08:00") || startTimeY.equals("12:00") || startTimeY.equals("16:00") || startTimeY.equals("20:00")))
					{
						System.out.println("No Flights Available due to bad weather!");
						if(choice == 2)
							((BookTicketSearch)f).setData(userNameY, passwordY, mobileNoY, genderY, fromY, toY, startMonthY, startTimeY, null, null, -999);
						else
							((DirectUserSearch)f).setData(fromY, toY, startMonthY, startTimeY, null, null, -999);
					}
					else if(fromY.equals("Chennai") && toY.equals("Ahmedabad") && startTimeY.equals("08:00"))
					{
						System.out.println("No of flights available: 1");
						System.out.println("\n1. Name: Chennai Airways  Price: 11125  Journey hours: 2:00");
						System.out.println("\nWhich flight would you prefer? ");
						choiceAirway = input.nextByte();
						switch(choiceAirway)
						{
							case 1:
							if(choice == 2)
							{
								System.out.print("Do you want to book ticket(y/n)? ");
								bookTicketChar = input.next().charAt(0);
								if(bookTicketChar == 'y')
								{
									bookTicketBoolean = true;
									System.out.println("Ticket Successfully booked!!");
								}
								else
									bookTicketBoolean = false;
							}
							else
							{
								bookTicketBoolean = false;
								System.out.println("Flight stored in history.");
							}
							if(choice == 2)
							{
								((BookTicketSearch)f).setData(userNameY, passwordY, mobileNoY, genderY, fromY, toY, startMonthY, startTimeY, "2:00", "Chennai Airways", 11125);
							}
							else
							{
								((DirectUserSearch)f).setData(fromY, toY, startMonthY, startTimeY, "2:00", "Chennai Airways", 11125);
							}
							break;
						}
					}
					else if(fromY.equals("Chennai") && toY.equals("Mumbai") && startTimeY.equals("16:00"))
					{
						System.out.println("No of flights available: 1");
						System.out.println("\n1. Name: Skyways  Price: 6500  Journey hours: 1:45");
						System.out.print("\nWhich flight would you prefer? ");
						choiceAirway = input.nextByte();
						switch(choiceAirway)
						{
							case 1:
							if(choice == 2)
							{
								System.out.print("Do you want to book ticket(y/n)? ");
								bookTicketChar = input.next().charAt(0);
								if(bookTicketChar == 'y')
								{
									bookTicketBoolean = true;
									System.out.println("Ticket Successfully booked!!");
								}
								else
									bookTicketBoolean = false;
							}
							else
							{
								bookTicketBoolean = false;
								System.out.println("Flight stored in history.");
							}
							if(choice == 2)
							{
								((BookTicketSearch)f).setData(userNameY, passwordY, mobileNoY, genderY, fromY, toY, startMonthY, startTimeY, "1:45", "Skyways", 6500);
							}
							else
							{
								((DirectUserSearch)f).setData(fromY, toY, startMonthY, startTimeY, "1:45", "Skyways", 6500);
							}
							break;
						}
					}
					else if(fromY.equals("Bangalore") && toY.equals("Ahmedabad") && (startTimeY.equals("16:00")))
					{
						System.out.println("No of flights available: 2");
						System.out.println("\n1. Name: Skyways  Price: 11500  Journey hours: 2:30");
						System.out.println("2. Name: Ola Airways  Price: 12000  Journey hours: 2:15");
						System.out.print("\nWhich flight would you prefer? ");
						choiceAirway = input.nextByte();
						switch(choiceAirway)
						{
							case 1:
							if(choice == 2)
							{
								System.out.print("Do you want to book ticket(y/n)? ");
								bookTicketChar = input.next().charAt(0);
								if(bookTicketChar == 'y')
								{
									bookTicketBoolean = true;
									System.out.println("Ticket Successfully booked!!");
								}
								else
									bookTicketBoolean = false;
							}
							else
							{
								System.out.println("Flight stored in history.");
								bookTicketBoolean = false;
							}
							if(choice == 2)
							{
								((BookTicketSearch)f).setData(userNameY, passwordY, mobileNoY, genderY, fromY, toY, startMonthY, startTimeY, "2:30", "Skyways", 11500);
							}
							else
							{
								((DirectUserSearch)f).setData(fromY, toY, startMonthY, startTimeY, "2:30", "Skyways", 11500);
							}
							break;
							case 2:
							if(choice == 2)
							{
								System.out.print("Do you want to book ticket(y/n)? ");
								bookTicketChar = input.next().charAt(0);
								if(bookTicketChar == 'y')
								{
									bookTicketBoolean = true;
									System.out.println("Ticket Successfully booked!!");
								}
								else
									bookTicketBoolean = false;
							}
							else
								bookTicketBoolean = false;
							if(choice == 2)
							{
								((BookTicketSearch)f).setData(userNameY, passwordY, mobileNoY, genderY, fromY, toY, startMonthY, startTimeY, "2:15", "Ola Airways", 12000);
							}
							else
							{
								((DirectUserSearch)f).setData(fromY, toY, startMonthY, startTimeY, "2:15", "Ola Airways", 12000);
							}
							break;
						}
					}
					else if(fromY.equals("Bangalore") && toY.equals("Chennai") && (startTimeY.equals("00:00") || startTimeY.equals("04:00") || startTimeY.equals("08:00") || startTimeY.equals("12:00") || startTimeY.equals("16:00") || startTimeY.equals("20:00")))
					{
						System.out.println("No Flights Available due to bad weather!");
						if(choice == 2)
							((BookTicketSearch)f).setData(userNameY, passwordY, mobileNoY, genderY, fromY, toY, startMonthY, startTimeY, null, null, -999);
						else
							((DirectUserSearch)f).setData(fromY, toY, startMonthY, startTimeY, null, null, -999);
					}
					else if(fromY.equals("Bangalore") && toY.equals("Delhi") && startTimeY.equals("4:00"))
					{
						System.out.println("No of flights available: 1");
						System.out.println("\n1. Name: Java Airways  Price: 12000  Journey hours: 2:45");
						System.out.print("\nWhich flight would you prefer? ");
						choiceAirway = input.nextByte();
						switch(choiceAirway)
						{
							case 1:
							if(choice == 2)
							{
								System.out.print("Do you want to book ticket(y/n)? ");
								bookTicketChar = input.next().charAt(0);
								if(bookTicketChar == 'y')
								{
									bookTicketBoolean = true;
									System.out.println("Ticket Successfully booked!!");
								}
								else
									bookTicketBoolean = false;
							}
							else
							{
								bookTicketBoolean = false;
								System.out.println("Flight stored in history.");
							}
							if(choice == 2)
							{
								((BookTicketSearch)f).setData(userNameY, passwordY, mobileNoY, genderY, fromY, toY, startMonthY, startTimeY, "2:45", "Java Airways", 12000);
							}
							else
							{
								((DirectUserSearch)f).setData(fromY, toY, startMonthY, startTimeY, "2:45", "Java Airways", 12000);
							}
							break;
						}
					}
					else if(fromY.equals("Bangalore") && toY.equals("Mumbai") && startTimeY.equals("20:00"))
					{
						System.out.println("No of flights available: 3");
						System.out.println("\n1. Name: Delhi Airways  Price: 6500  Journey hours: 1:30");
						System.out.println("\n2. Name: Skyways  Price: 7000  Journey hours: 1:15");
						System.out.println("\n3. Name: Java Airways  Price: 8000  Journey hours: 1:05");
						System.out.print("\nWhich flight would you prefer? ");
						choiceAirway = input.nextByte();
						switch(choiceAirway)
						{
							case 1:
							if(choice == 2)
							{
								System.out.print("Do you want to book ticket(y/n)? ");
								bookTicketChar = input.next().charAt(0);
								if(bookTicketChar == 'y')
								{
									bookTicketBoolean = true;
									System.out.println("Ticket Successfully booked!!");
								}
								else
									bookTicketBoolean = false;
							}
							else
							{
								bookTicketBoolean = false;
								System.out.println("Flight stored in history.");
							}
							if(choice == 2)
							{
								((BookTicketSearch)f).setData(userNameY, passwordY, mobileNoY, genderY, fromY, toY, startMonthY, startTimeY, "1:30", "Delhi Airways", 6500);
							}
							else
							{
								((DirectUserSearch)f).setData(fromY, toY, startMonthY, startTimeY, "1:30", "Delhi Airways", 6500);
							}
							break;
							case 2:
							if(choice == 2)
							{
								System.out.print("Do you want to book ticket(y/n)? ");
								bookTicketChar = input.next().charAt(0);
								if(bookTicketChar == 'y')
								{
									bookTicketBoolean = true;
									System.out.println("Ticket Successfully booked!!");
								}
								else
									bookTicketBoolean = false;
							}
							else
							{
								bookTicketBoolean = false;
								System.out.println("Flight stored in history.");
							}
							if(choice == 2)
							{
								((BookTicketSearch)f).setData(userNameY, passwordY, mobileNoY, genderY, fromY, toY, startMonthY, startTimeY, "1:15", "Skyways", 7000);
							}
							else
							{
								((DirectUserSearch)f).setData(fromY, toY, startMonthY, startTimeY, "1:15", "Skyways", 7000);
							}
							break;
							case 3:
							if(choice == 2)
							{
								System.out.print("Do you want to book ticket(y/n)? ");
								bookTicketChar = input.next().charAt(0);
								if(bookTicketChar == 'y')
								{
									bookTicketBoolean = true;
									System.out.println("Ticket Successfully booked!!");
								}
								else
									bookTicketBoolean = false;
							}
							else
							{
								bookTicketBoolean = false;
								System.out.println("Flight stored in history.");
							}
							if(choice == 2)
							{
								((BookTicketSearch)f).setData(userNameY, passwordY, mobileNoY, genderY, fromY, toY, startMonthY, startTimeY, "1:05", "Java Airways", 8000);
							}
							else
							{
								((DirectUserSearch)f).setData(fromY, toY, startMonthY, startTimeY, "1:05", "Java Airways", 8000);
							}
							break;
						}
					}
					else if(fromY.equals("Delhi") && toY.equals("Chennai") && startTimeY.equals("04:00"))
					{
						System.out.println("No of flights available: 1");
						System.out.println("\n1. Name: Delhi Airways  Price: 11500  Journey hours: 2:45");
						System.out.print("\nWhich flight would you prefer? ");
						choiceAirway = input.nextByte();
						switch(choiceAirway)
						{
							case 1:
							if(choice == 2)
							{
								System.out.print("Do you want to book ticket(y/n)? ");
								bookTicketChar = input.next().charAt(0);
								if(bookTicketChar == 'y')
								{
									bookTicketBoolean = true;
									System.out.println("Ticket Successfully booked!!");
								}
								else
									bookTicketBoolean = false;
							}
							else
							{
								bookTicketBoolean = false;
								System.out.println("Flight stored in history.");
							}
							if(choice == 2)
							{
								((BookTicketSearch)f).setData(userNameY, passwordY, mobileNoY, genderY, fromY, toY, startMonthY, startTimeY, "2:45", "Delhi Airways", 11500);
							}
							else
							{
								((DirectUserSearch)f).setData(fromY, toY, startMonthY, startTimeY, "2:45", "Delhi Airways", 11500);
							}
							break;
						}
					}
					else if(fromY.equals("Delhi") && toY.equals("Bangalore") && (startTimeY.equals("08:00")))
					{
						System.out.println("No of flights available: 2");
						System.out.println("\n1. Name: Skyways  Price: 10000  Journey hours: 2:45");
						System.out.println("2. Name: Java Airways  Price: 9500  Journey hours: 2:55");
						System.out.print("\nWhich flight would you prefer? ");
						choiceAirway = input.nextByte();
						switch(choiceAirway)
						{
							case 1:
							if(choice == 2)
							{
								System.out.print("Do you want to book ticket(y/n)? ");
								bookTicketChar = input.next().charAt(0);
								if(bookTicketChar == 'y')
								{
									bookTicketBoolean = true;
									System.out.println("Ticket Successfully booked!!");
								}
								else
									bookTicketBoolean = false;
							}
							else
							{
								System.out.println("Flight stored in history.");
								bookTicketBoolean = false;
							}
							if(choice == 2)
							{
								((BookTicketSearch)f).setData(userNameY, passwordY, mobileNoY, genderY, fromY, toY, startMonthY, startTimeY, "2:45", "Skyways", 10000);
							}
							else
							{
								((DirectUserSearch)f).setData(fromY, toY, startMonthY, startTimeY, "2:45", "Skyways", 10000);
							}
							break;
							case 2:
							if(choice == 2)
							{
								System.out.print("Do you want to book ticket(y/n)? ");
								bookTicketChar = input.next().charAt(0);
								if(bookTicketChar == 'y')
								{
									bookTicketBoolean = true;
									System.out.println("Ticket Successfully booked!!");
								}
								else
									bookTicketBoolean = false;
							}
							else
								bookTicketBoolean = false;
							if(choice == 2)
							{
								((BookTicketSearch)f).setData(userNameY, passwordY, mobileNoY, genderY, fromY, toY, startMonthY, startTimeY, "2:55", "Java Airways", 9000);
							}
							else
							{
								((DirectUserSearch)f).setData(fromY, toY, startMonthY, startTimeY, "2:55", "Java Airways", 9000);
							}
							break;
						}
					}
					else if(fromY.equals("Delhi") && toY.equals("Ahmedabad") && (startTimeY.equals("12:00")))
					{
						System.out.println("No of flights available: 2");
						System.out.println("\n1. Name: Delhi Airways  Price: 6500  Journey hours: 1:30");
						System.out.println("2. Name: Skyways  Price: 5000  Journey hours: 2:55");
						System.out.print("\nWhich flight would you prefer? ");
						choiceAirway = input.nextByte();
						switch(choiceAirway)
						{
							case 1:
							if(choice == 2)
							{
								System.out.print("Do you want to book ticket(y/n)? ");
								bookTicketChar = input.next().charAt(0);
								if(bookTicketChar == 'y')
								{
									bookTicketBoolean = true;
									System.out.println("Ticket Successfully booked!!");
								}
								else
									bookTicketBoolean = false;
							}
							else
							{
								System.out.println("Flight stored in history.");
								bookTicketBoolean = false;
							}
							if(choice == 2)
							{
								((BookTicketSearch)f).setData(userNameY, passwordY, mobileNoY, genderY, fromY, toY, startMonthY, startTimeY, "1:30", "Delhi Airways", 6500);
							}
							else
							{
								((DirectUserSearch)f).setData(fromY, toY, startMonthY, startTimeY, "1:30", "Delhi Airways", 6500);
							}
							break;
							case 2:
							if(choice == 2)
							{
								System.out.print("Do you want to book ticket(y/n)? ");
								bookTicketChar = input.next().charAt(0);
								if(bookTicketChar == 'y')
								{
									bookTicketBoolean = true;
									System.out.println("Ticket Successfully booked!!");
								}
								else
									bookTicketBoolean = false;
							}
							else
								bookTicketBoolean = false;
							if(choice == 2)
							{
								((BookTicketSearch)f).setData(userNameY, passwordY, mobileNoY, genderY, fromY, toY, startMonthY, startTimeY, "2:55", "Skyways", 5000);
							}
							else
							{
								((DirectUserSearch)f).setData(fromY, toY, startMonthY, startTimeY, "2:55", "Skyways", 5000);
							}
							break;
						}
					}
					else if(fromY.equals("Delhi") && toY.equals("Mumbai") && (startTimeY.equals("00:00") || startTimeY.equals("04:00") || startTimeY.equals("08:00") || startTimeY.equals("12:00") || startTimeY.equals("16:00") || startTimeY.equals("20:00")))
					{
						System.out.println("No Flights Available due to bad weather!");
						if(choice == 2)
							((BookTicketSearch)f).setData(userNameY, passwordY, mobileNoY, genderY, fromY, toY, startMonthY, startTimeY, null, null, -999);
						else
							((DirectUserSearch)f).setData(fromY, toY, startMonthY, startTimeY, null, null, -999);
					}
					else if(fromY.equals("Ahmedabad") && toY.equals("Chennai") && startTimeY.equals("00:00"))
					{
						System.out.println("No of flights available: 3");
						System.out.println("\n1. Name: Ahmedabad Airways  Price: 10000  Journey hours: 2:30");
						System.out.println("2. Name: Skyways  Price: 7000  Journey hours: 3:15");
						System.out.println("3. Name: Ola Airways  Price: 8000  Journey hours: 3:05");
						System.out.print("\nWhich flight would you prefer? ");
						choiceAirway = input.nextByte();
						switch(choiceAirway)
						{
							case 1:
							if(choice == 2)
							{
								System.out.print("Do you want to book ticket(y/n)? ");
								bookTicketChar = input.next().charAt(0);
								if(bookTicketChar == 'y')
								{
									bookTicketBoolean = true;
									System.out.println("Ticket Successfully booked!!");
								}
								else
									bookTicketBoolean = false;
							}
							else
							{
								bookTicketBoolean = false;
								System.out.println("Flight stored in history.");
							}
							if(choice == 2)
							{
								((BookTicketSearch)f).setData(userNameY, passwordY, mobileNoY, genderY, fromY, toY, startMonthY, startTimeY, "2:30", "Ahmedabad Airways", 10000);
							}
							else
							{
								((DirectUserSearch)f).setData(fromY, toY, startMonthY, startTimeY, "2:30", "Ahmedabad Airways", 10000);
							}
							break;
							case 2:
							if(choice == 2)
							{
								System.out.print("Do you want to book ticket(y/n)? ");
								bookTicketChar = input.next().charAt(0);
								if(bookTicketChar == 'y')
								{
									bookTicketBoolean = true;
									System.out.println("Ticket Successfully booked!!");
								}
								else
									bookTicketBoolean = false;
							}
							else
							{
								bookTicketBoolean = false;
								System.out.println("Flight stored in history.");
							}
							if(choice == 2)
							{
								((BookTicketSearch)f).setData(userNameY, passwordY, mobileNoY, genderY, fromY, toY, startMonthY, startTimeY, "3:15", "Skyways", 7000);
							}
							else
							{
								((DirectUserSearch)f).setData(fromY, toY, startMonthY, startTimeY, "3:15", "Skyways", 7000);
							}
							break;
							case 3:
							if(choice == 2)
							{
								System.out.print("Do you want to book ticket(y/n)? ");
								bookTicketChar = input.next().charAt(0);
								if(bookTicketChar == 'y')
								{
									bookTicketBoolean = true;
									System.out.println("Ticket Successfully booked!!");
								}
								else
									bookTicketBoolean = false;
							}
							else
							{
								bookTicketBoolean = false;
								System.out.println("Flight stored in history.");
							}
							if(choice == 2)
							{
								((BookTicketSearch)f).setData(userNameY, passwordY, mobileNoY, genderY, fromY, toY, startMonthY, startTimeY, "3:05", "Ola Airways", 8000);
							}
							else
							{
								((DirectUserSearch)f).setData(fromY, toY, startMonthY, startTimeY, "3:05", "Ola Airways", 8000);
							}
							break;
						}
					}
					else if(fromY.equals("Ahmedabad") && toY.equals("Bangalore") && startTimeY.equals("12:00"))
					{
						System.out.println("No of flights available: 1");
						System.out.println("\n1. Name: Ahmedabad Airways  Price: 11000  Journey hours: 3:00");
						System.out.print("\nWhich flight would you prefer? ");
						choiceAirway = input.nextByte();
						switch(choiceAirway)
						{
							case 1:
							if(choice == 2)
							{
								System.out.print("Do you want to book ticket(y/n)? ");
								bookTicketChar = input.next().charAt(0);
								if(bookTicketChar == 'y')
								{
									bookTicketBoolean = true;
									System.out.println("Ticket Successfully booked!!");
								}
								else
									bookTicketBoolean = false;
							}
							else
							{
								bookTicketBoolean = false;
								System.out.println("Flight stored in history.");
							}
							if(choice == 2)
							{
								((BookTicketSearch)f).setData(userNameY, passwordY, mobileNoY, genderY, fromY, toY, startMonthY, startTimeY, "3:00", "Ahmedabad Airways", 11000);
							}
							else
							{
								((DirectUserSearch)f).setData(fromY, toY, startMonthY, startTimeY, "3:00", "Ahmedabad Airways", 11000);
							}
							break;
						}
					}
					else if(fromY.equals("Ahmedabad") && toY.equals("Delhi") && (startTimeY.equals("00:00") || startTimeY.equals("04:00") || startTimeY.equals("08:00") || startTimeY.equals("12:00") || startTimeY.equals("16:00") || startTimeY.equals("20:00")))
					{
						System.out.println("No Flights Available due to bad weather!");
						if(choice == 2)
							((BookTicketSearch)f).setData(userNameY, passwordY, mobileNoY, genderY, fromY, toY, startMonthY, startTimeY, null, null, -999);
						else
							((DirectUserSearch)f).setData(fromY, toY, startMonthY, startTimeY, null, null, -999);
					}
					else if(fromY.equals("Ahmedabad") && toY.equals("Mumbai") && startTimeY.equals("04:00"))
					{
						System.out.println("No of flights available: 1");
						System.out.println("\n1. Name: Skyways  Price: 3500  Journey hours: 1:00");
						System.out.print("\nWhich flight would you prefer? ");
						choiceAirway = input.nextByte();
						switch(choiceAirway)
						{
							case 1:
							if(choice == 2)
							{
								System.out.print("Do you want to book ticket(y/n)? ");
								bookTicketChar = input.next().charAt(0);
								if(bookTicketChar == 'y')
								{
									bookTicketBoolean = true;
									System.out.println("Ticket Successfully booked!!");
								}
								else
									bookTicketBoolean = false;
							}
							else
							{
								bookTicketBoolean = false;
								System.out.println("Flight stored in history.");
							}
							if(choice == 2)
							{
								((BookTicketSearch)f).setData(userNameY, passwordY, mobileNoY, genderY, fromY, toY, startMonthY, startTimeY, "1:00", "Skyways", 3500);
							}
							else
							{
								((DirectUserSearch)f).setData(fromY, toY, startMonthY, startTimeY, "1:00", "Skyways", 3500);
							}
							break;
						}
					}
					else if(fromY.equals("Mumbai") && toY.equals("Chennai") && (startTimeY.equals("00:00") || startTimeY.equals("04:00") || startTimeY.equals("08:00") || startTimeY.equals("12:00") || startTimeY.equals("16:00") || startTimeY.equals("20:00")))
					{
						System.out.println("No Flights Available due to bad weather!");
						if(choice == 2)
							((BookTicketSearch)f).setData(userNameY, passwordY, mobileNoY, genderY, fromY, toY, startMonthY, startTimeY, null, null, -999);
						else
							((DirectUserSearch)f).setData(fromY, toY, startMonthY, startTimeY, null, null, -999);
					}
					else if(fromY.equals("Mumbai") && toY.equals("Bangalore") && startTimeY.equals("12:00"))
					{
						System.out.println("No of flights available: 1");
						System.out.println("\n1. Name: Mumbai Airways  Price: 5500  Journey hours: 1:45");
						System.out.print("\nWhich flight would you prefer? ");
						choiceAirway = input.nextByte();
						switch(choiceAirway)
						{
							case 1:
							if(choice == 2)
							{
								System.out.print("Do you want to book ticket(y/n)? ");
								bookTicketChar = input.next().charAt(0);
								if(bookTicketChar == 'y')
								{
									bookTicketBoolean = true;
									System.out.println("Ticket Successfully booked!!");
								}
								else
									bookTicketBoolean = false;
							}
							else
							{
								bookTicketBoolean = false;
								System.out.println("Flight stored in history.");
							}
							if(choice == 2)
							{
								((BookTicketSearch)f).setData(userNameY, passwordY, mobileNoY, genderY, fromY, toY, startMonthY, startTimeY, "1:45", "Mumbai Airways", 5500);
							}
							else
							{
								((DirectUserSearch)f).setData(fromY, toY, startMonthY, startTimeY, "1:45", "Mumbai Airways", 5500);
							}
							break;
						}
					}
					else if(fromY.equals("Mumbai") && toY.equals("Delhi") && startTimeY.equals("04:00"))
					{
						System.out.println("No of flights available: 3");
						System.out.println("\n1. Name: Ola Airways  Price: 7000  Journey hours: 2:00");
						System.out.println("2. Name: Skyways  Price: 6000  Journey hours: 2:15");
						System.out.println("3. Name: Mumbai Airways  Price: 5000  Journey hours: 3:05");
						System.out.print("\nWhich flight would you prefer? ");
						choiceAirway = input.nextByte();
						switch(choiceAirway)
						{
							case 1:
							if(choice == 2)
							{
								System.out.print("Do you want to book ticket(y/n)? ");
								bookTicketChar = input.next().charAt(0);
								if(bookTicketChar == 'y')
								{
									bookTicketBoolean = true;
									System.out.println("Ticket Successfully booked!!");
								}
								else
									bookTicketBoolean = false;
							}
							else
							{
								bookTicketBoolean = false;
								System.out.println("Flight stored in history.");
							}
							if(choice == 2)
							{
								((BookTicketSearch)f).setData(userNameY, passwordY, mobileNoY, genderY, fromY, toY, startMonthY, startTimeY, "2:00", "Ola Airways", 7000);
							}
							else
							{
								((DirectUserSearch)f).setData(fromY, toY, startMonthY, startTimeY, "2:00", "Ola Airways", 7000);
							}
							break;
							case 2:
							if(choice == 2)
							{
								System.out.print("Do you want to book ticket(y/n)? ");
								bookTicketChar = input.next().charAt(0);
								if(bookTicketChar == 'y')
								{
									bookTicketBoolean = true;
									System.out.println("Ticket Successfully booked!!");
								}
								else
									bookTicketBoolean = false;
							}
							else
							{
								bookTicketBoolean = false;
								System.out.println("Flight stored in history.");
							}
							if(choice == 2)
							{
								((BookTicketSearch)f).setData(userNameY, passwordY, mobileNoY, genderY, fromY, toY, startMonthY, startTimeY, "2:15", "Skyways", 6000);
							}
							else
							{
								((DirectUserSearch)f).setData(fromY, toY, startMonthY, startTimeY, "2:15", "Skyways", 6000);
							}
							break;
							case 3:
							if(choice == 2)
							{
								System.out.print("Do you want to book ticket(y/n)? ");
								bookTicketChar = input.next().charAt(0);
								if(bookTicketChar == 'y')
								{
									bookTicketBoolean = true;
									System.out.println("Ticket Successfully booked!!");
								}
								else
									bookTicketBoolean = false;
							}
							else
							{
								bookTicketBoolean = false;
								System.out.println("Flight stored in history.");
							}
							if(choice == 2)
							{
								((BookTicketSearch)f).setData(userNameY, passwordY, mobileNoY, genderY, fromY, toY, startMonthY, startTimeY, "3:05", "Mumbai Airways", 5000);
							}
							else
							{
								((DirectUserSearch)f).setData(fromY, toY, startMonthY, startTimeY, "3:05", "Mumbai Airways", 5000);
							}
							break;
						}
					}
					else if(fromY.equals("Mumbai") && toY.equals("Ahmedabad") && startTimeY.equals("08:00"))
					{
						System.out.println("No of flights available: 1");
						System.out.println("\n1. Name: Java Airways  Price: 3500  Journey hours: 1:00");
						System.out.print("\nWhich flight would you prefer? ");
						choiceAirway = input.nextByte();
						switch(choiceAirway)
						{
							case 1:
							if(choice == 2)
							{
								System.out.print("Do you want to book ticket(y/n)? ");
								bookTicketChar = input.next().charAt(0);
								if(bookTicketChar == 'y')
								{
									bookTicketBoolean = true;
									System.out.println("Ticket Successfully booked!!");
								}
								else
									bookTicketBoolean = false;
							}
							else
							{
								bookTicketBoolean = false;
								System.out.println("Flight stored in history.");
							}
							if(choice == 2)
							{
								((BookTicketSearch)f).setData(userNameY, passwordY, mobileNoY, genderY, fromY, toY, startMonthY, startTimeY, "1:00", "Java Airways", 3500);
							}
							else
							{
								((DirectUserSearch)f).setData(fromY, toY, startMonthY, startTimeY, "1:00", "Java Airways", 3500);
							}
							break;
						}
					}
					else
						System.out.println("No Flights available!");
					if(choice==1 || choice==2)
					{
						try
						{
							String string = String.valueOf(refNoY) + ".dat";
							FileOutputStream fOut = new FileOutputStream(string);
							ObjectOutputStream oOut = new ObjectOutputStream(fOut);
							oOut.writeObject(f);
						}
						catch(IOException e)
						{
							System.out.println("Error writing to file. Error Desc: " + e);
						}
						/*finally
						{
							oOut.close();
						}*/
					}
				}
				if(choice == 3)
				{
					try
					{
						String refNoInputX;
						System.out.print("Enter your ref no: ");
						refNoInputX = input.next();
						String refNoInputS = refNoInputX + ".dat";
						FileInputStream fIn = new FileInputStream(refNoInputS);
						ObjectInputStream oIn = new ObjectInputStream(fIn);
						DirectUserSearch d;
						d = (DirectUserSearch) oIn.readObject();
						d.display();
					}
					catch(Exception e)
					{
						//System.out.println("Error occured. Error desc: " + e);
					}
				}
				if(choice == 4)
				{
					String userNameInputX;
					String passwordInputX;
					int refNoInputZ;
					System.out.println("Enter ref no: ");
					refNoInputZ = input.nextInt();
					String refNoInputString = String.valueOf(refNoInputZ) + ".dat";
					System.out.print("Enter username: ");
					userNameInputX = input.next();
					System.out.print("Enter password: ");
					passwordInputX = input.next();
					String refNoString = String.valueOf(refNoInputZ) + ".dat";
					for(int j=0; j<1000; j++)
					{
						if(f.compare(userNameInputX, passwordInputX, refNoInputZ));
						{
							try
							{
								ObjectInputStream oIn = new ObjectInputStream(new FileInputStream(refNoString));
								BookTicketSearch b;
								b = (BookTicketSearch) oIn.readObject();
								b.display();
								flag = 1;
								break;
							}
							catch(Exception e)
							{
								//System.out.println("Error occured. Error Desc: " + e);
							}
						}
					}
					if(flag == 1)
					{
						System.out.println("Invalid Input. ");
					}
				}
			}
			else if(choiceInput == 2)
			{	
				System.out.print("______________________________________________________\n");
				System.out.print("\n\t1. Search Direct User Search Records \n\t2. Search Book Ticket User Search Records. \n\n\t");
				System.out.print("Enter your choice: ");
				choice = input.nextByte();
				if(choice == 1)
				{
					System.out.print("Enter your reference no: ");
					String srefNo = input.next();
					String string = srefNo + ".dat";
					try
					{
						FileInputStream fIn = new FileInputStream(string);
						ObjectInputStream oIn = new ObjectInputStream(fIn);
						DirectUserSearch d;
						d = (DirectUserSearch) oIn.readObject();
						d.display();
					}
					catch(Exception e)
					{
						System.out.println("Error reading from file. Error Desc: " + e);
					}
				}
				if(choice == 2)
				{
					System.out.print("Enter your reference no: ");
					String srefNo = input.next();
					String string = srefNo + ".dat";
					try
					{
						FileInputStream fIn = new FileInputStream(string);
						ObjectInputStream oIn = new ObjectInputStream(fIn);
						BookTicketSearch b;
						b = (BookTicketSearch) oIn.readObject();
						b.display();
					}
					catch(Exception e)
					{
						System.out.println("Error reading from file. Error Desc: " + e);
					}
				}
			}
		}while(choiceInput!=0);
	}
}