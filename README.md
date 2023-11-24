# Tasks Management Console Application
### Project Description
This project is a Tasks Management console application designed and implemented for use by small teams of developers. Its primary purpose is to help teams keep track of tasks related to a software product they are building. The application supports multiple teams, each with its own members, boards, and tasks of three types: Bug, Story, and Feedback.
## Functional Requirements
---Teams--- 
Each team must have a unique name, members, and boards.
Each member must have a unique name, a list of tasks, and activity history.
Each board must have a unique name, a list of tasks, and activity history.
## Tasks
### Bug

Bugs have an ID, title, description, steps to reproduce, priority, severity, status, assignee, comments, and a changes history.
Title is a string between 10 and 100 symbols.
Description is a string between 10 and 500 symbols.
Steps to reproduce is a list of strings.
Priority is one of High, Medium, or Low.
Severity is one of Critical, Major, or Minor.
Status is one of Active or Done.
Assignee is a member from the team.
Comments are a list of comments (string messages with an author).
History is a list of all changes (string messages) done to the bug.
### Story

Stories have an ID, title, description, priority, size, status, assignee, comments, and a changes history.
Title is a string between 10 and 100 symbols.
Description is a string between 10 and 500 symbols.
Priority is one of High, Medium, or Low.
Size is one of Large, Medium, or Small.
Status is one of Not Done, InProgress, or Done.
Assignee is a member from the team.
Comments are a list of comments (string messages with an author).
History is a list of all changes (string messages) done to the story.
 ### Feedback

Feedbacks have an ID, title, description, rating, status, comments, and a changes history.
Title is a string between 10 and 100 symbols.
Description is a string between 10 and 500 symbols.
Rating is an integer.
Status is one of New, Unscheduled, Scheduled, or Done.
Comments are a list of comments (string messages with an author).
History is a list of all changes (string messages) done to the feedback.
Note
IDs of tasks must be unique in the application.

## Operations

The application supports the following operations:

## People
Create a new person.
Show all people.
Show a person's activity.
## Teams
Create a new team.
Show all teams.
Show team activity.
## Members and Teams
Add a person to a team.
Show all team members.
## Boards
Create a new board in a team.
Show all team boards.
Show board activity.
## Tasks
Create a new Bug/Story/Feedback in a board.
Change the Priority/Severity/Status of a bug.
Change the Priority/Size/Status of a story.
Change the Rating/Status of a feedback.
Assign/Unassign a task to a person.
Add a comment to a task.
## Listing
List all tasks (display the most important info).
Filters and Sorting
## Generic

Filter by title.
Sort by title.
Task-Specific

List bugs/stories/feedback only.
Bug-Specific

Filter by status and/or assignee.
Sort by title/priority/severity.
Story-Specific

Filter by status and/or assignee.
Sort by title/priority/size.
Feedback-Specific

Filter by status and/or assignee.
Sort by title/rating.
Contributors

Kalina B.
Ivaylo V.
Siyana B.
