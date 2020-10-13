# User Guide
Duke is a **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI). Duke can ensure your productivity through management of various tasks if you can type fast.
- [Quick Start](#quick-start)
- [Features](#features)
  - [Viewing help: `help`](#viewing-help-help)
  - [Adding a todo task : `todo`](#adding-a-todo-task-todo)
  - [Adding a deadline task: `deadline`](#adding-a-deadline-task-deadline)
  - [Adding an event task: `event`](#adding-an-event-task-event)
  - [Listing all tasks: `list`](#listing-all-tasks-list)
  - [Marking a task as done: `done`](#marking-a-task-as-done-done)
  - [Deleting a task: `delete`](#deleting-a-task-delete)
  - [Locating tasks by name: `find`](#locating-tasks-by-name-find)
  - [Exiting the program: `bye`](#exiting-the-program-exit)
  - [Saving the data](#saving-the-data)
- [FAQ](#faq)
- [Command summary](#command-summary)
<br/><br/>
## Quick Start
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `ip.jar` from [here](https://github.com/czlin98/ip/releases/tag/A-Release).
3. Copy the file to the folder you want to use as the *home folder* for your Duke app.
4. Open a command window in that folder.
5. Run the command `java -jar ip.jar`. The UI similar to the below should appear.

![welcome](https://raw.githubusercontent.com/czlin98/ip/master/docs/duke_welcome.png)
6. Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will show the list of available commands.\
   Some example commands you can try:
   - `event project meeting /at 2/10/2020 1400` : Adds an event task with date Oct 2 2020 and time 2:00 pm to the task list.
   - `list` : Lists all tasks.
   - `done 1` : Marks the 1st task shown in the current list.
   - `delete 1` : Deletes the 1st task shown in the current list.
   - `bye` : Exits the app.
7. Refers to the Features below for details of each command.
<br/><br/>
## Features

> :information_source: Notes about the command format:
>- Words in `UPPER_CASE` are the parameters to be supplied by the user.
>  e.g. in `todo TASK`, `TASK` is a parameter which can be used as `add borrow book`.
>- Items in square brackets are optional.
>  e.g. `/by DATE [TIME]` can be used as `/by 2/10/2020 1800` or as `/by 2/10/2020`.
>- Parameters have to be in order.


### Viewing help: `help`
Shows a list of available commands.

Format: `help`
<br/><br/>
### Adding a todo task: `todo`
Adds a todo task to the task list.

Format: `todo TASK`

Example:
- `todo borrow book`
<br/><br/>
### Adding a deadline task: `deadline`
Adds a deadline task to the task list.

Format: `deadline TASK /by DATE [TIME]`

Examples:
- `deadline borrow book /by 2/10/2020`
- `deadline return book /by 9/10/2020 1800`
<br/><br/>
### Adding an event task: `event`
Adds an event task to the task list.

Format: `event TASK /at DATE [TIME]`

Examples:
- `event project meeting /at 2/10/2020 1400`
- `event John Doe's birthday /at 1/01/2021`
<br/><br/>
### Listing all tasks: `list`
Shows a list of all tasks in the task list.

Format: `list`
<br/><br/>
### Marking a task as done: `done`
Marks the specified task as done in the task list.

Format: `done INDEX`
- Marks the task at the specified `INDEX` as done.
- The index refers to the index number shown in the task list.
- The index **must be a positive integer** 1, 2, 3...

Example:
- `list` followed by `delete 1` marks the first task in the task list as done.
<br/><br/>
### Deleting a task: `delete`
Deletes the specified task from the task list.

Format: `delete INDEX`
- Deletes the task at the specified `INDEX`
- The index refers to the index number shown in the task list.
- The index **must be a positive integer** 1, 2, 3...

Example:
- `list` followed by `delete 2` deletes the second task in the task list.
<br/><br/>
### Locating tasks by name: `find`
Finds tasks whose descriptions contain any of the given keywords.

Format: `find KEYWORD`
- The search is case-insensitive. e.g. `Borrow Book` will match `borrow book`
- The order of the keywords matter. e.g. `book borrow` will not match `borrow book`
- Only the description is searched.
- Partial words will be matched. e.g. `bor` will match `borrow book`
- Only tasks matching the all the keywords will be returned. e.g. `borrow return` will not match `borrow book, return book`

Examples:
- `find book` returns `borrow book` and `return book`
- `find bor` returns `borrow book`

![find](https://raw.githubusercontent.com/czlin98/ip/master/docs/duke_find.png)
<br/><br/>
### Exiting the program: `bye`
Exits the program.

Format: `bye`
<br/><br/>
### Saving the data
Duke data are saved in the hard disk automatically after any command that changes the data.\
There is no need to save manually.
<br/><br/>
## FAQ
**Q:** How do I transfer my data to another Computer?\
**A:** Install the app in the other computer and copy into the same folder the `duke.txt` file that contains the data of your previous Duke app.
<br/><br/>
## Command summary

| **Action** | **Format, Examples** |
| ---------- | -------------------- |
| **Add deadline** | `deadline TASK /by DATE [TIME]`<br/>e.g. `event project meeting /at 2/10/2020 1400`, `deadline return book /by 9/10/2020 1800` |
| **Add event** | `event TASK /at DATE [TIME]`<br/>e.g. `event project meeting /at 2/10/2020 1400`, `event John Doe's birthday /at 1/01/2021` |
| **Add todo** | `todo TASK`<br/>e.g. `todo borrow book` |
| **Delete** | `delete INDEX`<br/>e.g. `delete 2` |
| **Done** | `done INDEX`<br/>e.g. `done 1` |
| **Find** | `find KEYWORD`<br/>e.g. `find book` |
| **List** | `list` |
| **Help** | `help` |