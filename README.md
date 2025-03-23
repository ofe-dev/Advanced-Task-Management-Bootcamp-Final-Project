# Advanced Task Management System

A comprehensive task management system built with Spring Boot that allows organizations to manage projects, tasks, and team members efficiently.

## Features

- User Authentication and Authorization
- Project Management
- Task Management with State Tracking
- File Attachments
- Team Member Management
- Role-based Access Control

## Technologies

- Java 21
- Spring Boot 3.x
- Spring Security with JWT
- Spring Data JPA
- Maven

## API Documentation

### Authentication Endpoints

#### Authenticate User
```http
POST /api/auth/authenticate
```
Request body:
```json
{
    "username": "admin",
    "password": "123456"
}
```

### User Operations Endpoints

#### Create Admin
```http
POST /api/user/CreateAdmin
```
Request body:
```json
{
    "username": "admin2",
    "password": "12345",
    "role": "ROLE_ADMIN"
}
```

#### Create User
```http
POST /api/user/CreateAdmin
```
Request body:
```json
{
    "username": "pm4",
    "password": "123456",
    "role": "ROLE_PROJECT_MANAGER"
}
```

#### Change Password
```http
PUT /api/user/ChangePassword
```
Request body:
```json
{
    "oldPassword": "234567",
    "newPassword": "123456",
    "newPasswordConfirmation": "123456"
}
```

### Task Operations Endpoints

#### Create Task
```http
POST /api/task/createtask
```
Request body:
```json
{
    "userStoryDescription": "story description3",
    "acceptanceCriteria": "finish all3",
    "priority": "MEDIUM",
    "teamMemberId": 18,
    "projectId": 1
}
```

#### Update Task State
```http
PUT /api/task/UpdateState
```
Request body:
```json
{
    "taskId": 1,
    "newState": "IN_DEVELOPMENT"
}
```

#### Update Task Priority
```http
PUT /api/task/UpdatePriority
```
Request body:
```json
{
    "taskId": 1,
    "newPriority": "HIGH"
}
```

#### Add Comment
```http
POST /api/task/AddComment
```
Request body:
```json
{
    "taskId": 1,
    "content": "comment"
}
```
#### Delete Comment
```http
DELETE /api/task/DeleteComment
```
Request body:
```json
{
    "commentId": 2
}
```

#### Attach File
```http
POST /api/task/AttachFile
```
Request body:
```json
{
    "taskId": 1,
    "file": file
}
```
#### Delete File
```http
DELETE /api/task/DeleteFile
```
Request body:
```json
{
    "attachmentId": 2
}
```

### Project Operations Endpoints

#### Create Project
```http
POST /api/project/CreateProject
```
Request body:
```json
{
    "name": "Project Name",
    "description": "Project Description",
    "projectManagerId": 1
}
```

#### Update Project
```http
PUT /api/project/UpdateProject
```
Request body:
```json
{
    "projectId": 1,
    "name": "Updated Project Name",
    "description": "Updated project description with more details",
    "responsibleDepartmentName": "Engineering Department",
    "status": "IN_PROGRESS"
}
```

#### Get Project Tasks
```http
GET /api/project/GetProjectTasks
```
Request body:
```json
{
    "projectId": 1
}
```

#### Get Team Tasks
```http
GET /api/project/GetTeamTasks
```
Request body:
```json
{
   
}
```

#### Get Member Tasks
```http
GET /api/project/GetMemberTasks
```
Request body:
```json
{
   
}
```


## Role-Based Access Control

The system implements three roles with different permissions:

### ROLE_TEAM_MEMBER
- View assigned tasks
- Update task states
- Add comments
- Attach files

### ROLE_TEAM_LEADER
- All Team Member permissions
- View all team tasks

### ROLE_PROJECT_MANAGER
- All Team Leader permissions
- Create projects
- Update project details
- View all projects and tasks
- Manage team members

## Task State Flow

Tasks follow a specific state flow:

1. BACKLOG (Initial state)
2. IN_ANALYSIS
3. IN_DEVELOPMENT
4. COMPLETED

Special states:
- BLOCKED (Requires reason)
- CANCELLED (Requires reason)



## Security

- JWT-based authentication
- Password encryption using BCrypt
- Role-based access control
- Input validation
- File upload restrictions
