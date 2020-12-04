# CS3343 Lions3 (Group 3)

![N|Solid](https://www.shareicon.net/data/256x256/2015/11/25/677545_running_512x512.png)

![GitHub commit activity](https://img.shields.io/github/commit-activity/y/iliasbatyrbekov/Lions) ![GitHub contributors](https://img.shields.io/github/contributors/iliasbatyrbekov/Lions) ![GitHub Repo stars](https://img.shields.io/github/stars/iliasbatyrbekov/Lions?style=social) ![GitHub forks](https://img.shields.io/github/forks/iliasbatyrbekov/Lions?style=social) 

# Motivation

One of the most famous personal finance quotes by Warren Buffett is:
>Do Not Save What Is Left After Spending, Spend What Is Left After Saving

It clearly points out the importance of financial management. During this era, there are multiple ways to spend and earn money, and there are even more types of currency. We aim to build a simple application to record the daily expense and income, furthermore, we will help customers to manage their finance by offering a saving plan or provide an overview of what they have spent.


# Features
**Lions3** is a fastforward and user friendly finance management system, it has following features:

  - Personal Finance Management System
    - Record Finacne
    - View Finance
    - Plan Finance
  - Corporation Accounting System
    - Accounting Cycle
    - Inventory Management


# Installation & Run

Dillinger requires [Java SE](https://www.oracle.com/java/technologies/javase-downloads.html) and [Java SE Development Kit](https://www.oracle.com/java/technologies/javase-downloads.html) to run.
*This software recommend users install Java SE Runtime Environment version >= 8 (JRE8), lower version is not guarantee to work.*

To chechk JRE version on your local machine, please turn on your cmd/ terminal and run
```sh
$ java -version
```

To run the program, please make sure there are three file in your environment
1. testCorporate.txt *(A txt file which including multiple corpporation-level user input)*
2. testPersonal.txt *(individual user input file)*
3. execute.sh (execute.bat for windows user, for executing lions3.jar)
4. lions3.jar (File to run java code)

! ! *`>` is for windows user/ `$` is for unix-like user*/ `#` is the output text
## Start Executing

```sh
$ sh execute.sh
> execute
```

you will see a confirmation text to ask you if you want to go on further, press **ENTER** to proceed

### Personal
```sh
# Please select the accounting system to use (personal/corporate):
$ personal

# Please input the file pathname:
$ testPersonal.txt
```

### Corporation
```sh
# Please select the accounting system to use (personal/corporate):
$ corporate
```

**ERROR?**
if normal start up can't run the jar projecr, please run to initial the java project
```
java -jar lions3.jar
```

# User GuideLine
### TestPersonal.txt
| Execution Name | Parameters | Description |
| ----- | ------ | ---- |
| Add Account | (Account Type, ID, balance, debt, interest, withdraw date)  | This is a function to add a variety of accounts and record inside the accounts list of the user. There are four types of account which will also be differentiated inside this function.
| Add Plan | (Plan Type, Name, Start date, End date, Goal Amount, Interest Rate, Debt Owner, Description)  | The plan has three different types and each type has its corresponding parameters. This function will create a plan based on the type specified by the user and added to the plan list.
| Record Transaction | (Transaction Type, amount, Account ID, Plan ID, Description, Date, Category)  | This function is for the user to add transactions and recorded them in the user transaction history. Every transaction can specify the account and the plan which the user want to recorded in.
| List All Account | NULL | list all the accounts for this user
| List All Plan | NULL | list all the plans for this user
| List Transactions | NULL | list all the transactions for this user
| View Expense By Account | NULL | The user can view the expense by each account
| View Expense By Category | NULL | The user can view the expense by each category
| View Expense By Member | NULL | The user can view the expense by each member
| View Plan Detail | Plan ID | The user can view a single plan detail, and the content of the detail will be changed refer to the plan type.
| Saving Plan Get Average Saving | NULL | It's a utility function to calculate the average saving amount of giving duration of time |

### TestCorporation.txt
| Execution Name | Parameters | Description |
| ----- | ------ | ---- |
| a | f | c |


# Techs

Lions3 using following techniques and software for development.

| Tech | Link | Description |
| ------ | ------ | ---- |
| GitHub | https://github.com/ | code hosting platform |
| Git | https://git-scm.com/ | version control |
| Google Drive | https://www.google.com.hk/drive/about.html | Cloud Storage |
| Eclipse | https://www.eclipse.org/downloads/ | Java IDE |
| Gitkraken | https://www.gitkraken.com/ | Git GUI |
| JUnit | https://junit.org/junit5/ | Java testing |
| Bugzilla | https://www.bugzilla.org/ | Bugs reporting |
