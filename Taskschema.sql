-- Drop Database, this is to make sure everything refreshes
DROP DATABASE IF EXISTS Task;

-- Create the Task Database if it does not already exist
CREATE DATABASE IF NOT EXISTS Task;

-- Switch to the Task Database
USE Task;

-- Create the task Table
CREATE TABLE IF NOT EXISTS Task (
    taskId INT AUTO_INCREMENT,
    title VARCHAR(128) NOT NULL,
    dueDate DATE,
    priority INT,
    completed BOOLEAN default false,
    
    PRIMARY KEY (taskId)
);
