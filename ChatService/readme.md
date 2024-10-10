### USER TABLE
| **Field**       | **Type** | **Description**                     |
|-----------------|----------|-------------------------------------|
| ID              | Integer  | Unique identifier for the user      |
| FIRST NAME      | String   | User's first name                   |
| LAST NAME       | String   | User's last name                    |
| USERNAME        | String   | Unique username                     |
| EMAIL           | String   | User's email (optional but unique)  |
| LAST LOGIN DATE | String   | User's last login time              |
| LOGOUT DATE     | String   | User's offline time                 |

---

### CHAT TABLE
| **Field**      | **Type**       | **Description**                                  |
|----------------|----------------|--------------------------------------------------|
| ID             | Number         | Unique identifier for the chat                   |
| NAME           | String         | Chat name or title                               |
| AVATAR         | String (URL)   | Avatar image URL for the chat                    |
| CREATED_AT     | DateTime       | Timestamp of when the chat was created           |
| MODIFIED_AT    | DateTime       | Timestamp of the last modification               |
| TYPE           | String         | Chat type (e.g., group, direct message, etc.)    |
| LAST_MESSAGE   | MESSAGE        | The last message sent in the chat                |
| LAST_ACTIVITY  | DateTime       | Timestamp of the last activity in the chat       |

---

### PARTICIPANT TABLE
| **Field**    | **Type** | **Description**                         |
|--------------|----------|-----------------------------------------|
| ID           | Integer  | Unique identifier for the relation      |
| CHAT ID      | Integer  | Foreign key to the chat                 |
| USER ID      | Integer  | Foreign key to the user                 |
| ROLE         | String   | Role of the participant (admin/member)  |
| JOINED_AT    | DateTime | Timestamp when the user joined the chat |
| UNREAD_COUNT | Integer  | Number of unread messages for the user  |

---

### CONVERSATION TABLE
| **Field** | **Type** | **Description**                         |
|-----------|----------|-----------------------------------------|
| ID        | Number   | Unique identifier for the conversation  |
| CHAT ID   | Number   | Chat ID for separate chat room          |
| PARENT_ID | Number   | ID for parent message (if it's a reply) |

---

### MESSAGE TABLE
| **Field**       | **Type**     | **Description**                                 |
|-----------------|--------------|-------------------------------------------------|
| ID              | Number       | Unique identifier for the conversation          |
| CONVERSATION ID | Number       | Conversation ID for individual message          |
| TYPE            | String       | Type of message (**TEXT / ATTACHMENT / EMOJI**) |
| SENDER          | String       | Sender username                                 |
| TIMESTAMP       | Datetime     | Message sent time                               |

---

### MESSAGE STATUS TABLE
| **Field**  | **Type** | **Description**                              |
|------------|----------|----------------------------------------------|
| ID         | Integer  | Unique identifier for the relation           |
| MESSAGE ID | Integer  | Foreign key to the message                   |
| USER ID    | Integer  | Foreign key to the user who read the message |
| TIMESTAMP  | DateTime | Time when the user read the message          |
| STATUS     | String   | Message status                               |

---

### MESSAGE CONTENT TABLE
| **Field**  | **Type** | **Description**                        |
|------------|----------|----------------------------------------|
| ID         | Number   | Unique identifier for the conversation |
| MESSAGE ID | Number   | Foreign key for message                |
| CONTENT    | String   | Text message content                   |

---

### ATTACHMENT TABLE
| **Field**  | **Type** | **Description**                        |
|------------|----------|----------------------------------------|
| ID         | Number   | Unique identifier for the conversation |
| MESSAGE ID | Number   | Foreign key for message                |
| FILE NAME  | String   | Attachment name                        |
| FILE URL   | String   | File URL                               |
| FILE TYPE  | String   | Type of the file (image, video, doc)   |
