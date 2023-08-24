# Mini Hacker News

## Overview

The Hacker News website is down due to a DDOS attack, and we need to quickly create a mini version of it. This project
involves building a simple RESTful JSON API for posting, upvoting, and viewing posts.

## Specifications

- Create posts with a single text field.
- Implement a JSON API for post management.
- Support upvoting of posts.
- Use a programming language and storage engine of your choice.
- Set up a local git repository to track progress.

## Docker Compose Setup

To simplify deployment, a `docker-compose.yml` file is provided in the `docker` folder.

## Implemented API Calls

### GET Today's News

- Endpoint: `localhost:8080/api/news/today`

### GET Yesterday's News

- Endpoint: `localhost:8080/api/news/yesterday`

### Search for News

- Endpoint: `localhost:8080/api/news/search`
- Query Params:
    - `keyword`: Fashion

### Post a Comment

- Endpoint: `localhost:8080/api/news/9/comments`
- Method: POST
- Body (JSON):
  ```json
  {
      "text": "I love the latest fashion trends!"
  }

### Upvote a News Item

- Endpoint: `localhost:8080/api/news/9/upvote`
- Method: PUT

### Post a News Item

- Endpoint: `localhost:8080/api/news/`
- Method: POST
- Body (JSON):
  ```json
  {
      "title": "Exploring the Elegance of Borzoi Dogs",
      "link": "https://www.example.com/borzoi-dog-elegance"
  }