import { AfterViewChecked, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Message } from '../_model/message';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.scss',
})
export class ChatComponent implements OnInit, AfterViewChecked  {
  messages: Message[] = [];
  @ViewChild('messageContainer') private messageContainer!: ElementRef;

  ngAfterViewChecked() {
    this.scrollToBottom();
  }

// <div class="chat-container">
//   <div class="messages">
//     <div *ngFor="let message of chatData.messages" 
//          [ngClass]="{'message': true, 'sent': isCurrentUser(message.sender_id), 'received': !isCurrentUser(message.sender_id)}">
//       <img [src]="getProfilePic(message.sender_id)" alt="Profile Picture" class="profile-pic">
//       <div class="message-content">
//         <p>{{ message.text }}</p>
//         <span class="timestamp">{{ formatTimestamp(message.timestamp) }}</span>
//       </div>
//     </div>
//   </div>
// </div>

  ngOnInit(): void {
    this.messages = [
      {
        text: 'Hey! Listen',
        sender: 'me',
        timestamp: new Date(new Date().getTime() - 1 * 60 * 1000), // 1 minute ago
        profilePic:
          'https://img.freepik.com/free-photo/beautiful-girl-stands-park_8353-5084.jpg?size=626&ext=jpg&ga=GA1.1.2008272138.1726963200&semt=ais_hybrid',
      },
      {
        text: 'I really like your idea, but I still think we can do more in this ',
        sender: 'me',
        timestamp: new Date(new Date().getTime() - 5 * 60 * 1000), // 5 minutes ago
        profilePic:
          'https://img.freepik.com/free-photo/beautiful-girl-stands-park_8353-5084.jpg?size=626&ext=jpg&ga=GA1.1.2008272138.1726963200&semt=ais_hybrid',
      },
      {
        text: 'I will share something',
        sender: 'me',
        timestamp: new Date(new Date().getTime() - 10 * 60 * 1000), // 10 minutes ago
        profilePic:
          'https://img.freepik.com/free-photo/beautiful-girl-stands-park_8353-5084.jpg?size=626&ext=jpg&ga=GA1.1.2008272138.1726963200&semt=ais_hybrid',
      },
      {
        text: "Let's together work on this and create something more awesome.",
        sender: 'other',
        timestamp: new Date(new Date().getTime() - 12 * 60 * 1000), // 12 minutes ago
        profilePic:
          'https://img.freepik.com/free-photo/beautiful-girl-stands-park_8353-5084.jpg?size=626&ext=jpg&ga=GA1.1.2008272138.1726963200&semt=ais_hybrid',
      },
      {
        text: 'Sounds perfect!',
        sender: 'me',
        timestamp: new Date(new Date().getTime() - 15 * 60 * 1000), // 15 minutes ago
        profilePic:
          'https://img.freepik.com/free-photo/beautiful-girl-stands-park_8353-5084.jpg?size=626&ext=jpg&ga=GA1.1.2008272138.1726963200&semt=ais_hybrid',
      },
      {
        text: 'Ok will talk to you later.',
        sender: 'other',
        timestamp: new Date(new Date().getTime() - 12 * 60 * 1000), // 12 minutes ago
        profilePic:
          'https://img.freepik.com/free-photo/beautiful-girl-stands-park_8353-5084.jpg?size=626&ext=jpg&ga=GA1.1.2008272138.1726963200&semt=ais_hybrid',
      },
      {
        text: 'Ok will talk to you later.',
        sender: 'other',
        timestamp: new Date(new Date().getTime() - 12 * 60 * 1000), // 12 minutes ago
        profilePic:
          'https://img.freepik.com/free-photo/beautiful-girl-stands-park_8353-5084.jpg?size=626&ext=jpg&ga=GA1.1.2008272138.1726963200&semt=ais_hybrid',
      },
      {
        text: 'Ok will talk to you later.',
        sender: 'me',
        timestamp: new Date(new Date().getTime() - 12 * 60 * 1000), // 12 minutes ago
        profilePic:
          'https://img.freepik.com/free-photo/beautiful-girl-stands-park_8353-5084.jpg?size=626&ext=jpg&ga=GA1.1.2008272138.1726963200&semt=ais_hybrid',
      },
    ];
  }

  scrollToBottom(): void {
    try {
      this.messageContainer.nativeElement.scrollTop = this.messageContainer.nativeElement.scrollHeight;
    } catch (err) {
      console.error('Could not scroll to bottom', err);
    }
  }

  // Function to calculate the time difference (e.g., "1 minute ago")
  timeAgo(date: Date): string {
    const now = new Date();
    const diff = Math.floor((now.getTime() - date.getTime()) / 1000 / 60); // difference in minutes

    if (diff < 1) return 'Just now';
    if (diff === 1) return '1 minute ago';
    return `${diff} minutes ago`;
  }

  chats = [
    {
      id: 1,
      name: 'Ankit Mishra',
      lastMessage: 'Are we meeting today?',
      avatar:
        'https://img.freepik.com/free-photo/beautiful-girl-stands-park_8353-5084.jpg?size=626&ext=jpg&ga=GA1.1.2008272138.1726963200&semt=ais_hybrid',
        isActive: true
    },
    {
      id: 2,
      name: 'Asanksha Sinha',
      lastMessage: "I'll update you the docs",
      avatar:
        'https://img.freepik.com/free-photo/beautiful-girl-stands-park_8353-5084.jpg?size=626&ext=jpg&ga=GA1.1.2008272138.1726963200&semt=ais_hybrid',
        isActive: false
    },
    {
      id: 3,
      name: 'Harshit Nagar',
      lastMessage: 'Can you send me some...',
      avatar:
        'https://img.freepik.com/free-photo/beautiful-girl-stands-park_8353-5084.jpg?size=626&ext=jpg&ga=GA1.1.2008272138.1726963200&semt=ais_hybrid',
        isActive: true
    },
    
  ];

  notifications = [
    {
      id: 1,
      name: '@kirti',
      action: 'mentioned you in Trip to Goa',
      time: '1 hour ago',
      avatar:
        'https://img.freepik.com/free-photo/beautiful-girl-stands-park_8353-5084.jpg?size=626&ext=jpg&ga=GA1.1.2008272138.1726963200&semt=ais_hybrid',
    },
    {
      id: 2,
      name: '@harshita',
      action: 'added you to "Design Study"',
      time: '2 hours ago',
      avatar:
        'https://img.freepik.com/free-photo/beautiful-girl-stands-park_8353-5084.jpg?size=626&ext=jpg&ga=GA1.1.2008272138.1726963200&semt=ais_hybrid',
    },
    {
      id: 3,
      name: '@samsmith',
      action: 'removed you from Group "Work"',
      time: '3 hours ago',
      avatar:
        'https://img.freepik.com/free-photo/beautiful-girl-stands-park_8353-5084.jpg?size=626&ext=jpg&ga=GA1.1.2008272138.1726963200&semt=ais_hybrid',
    },
    {
      id: 4,
      name: '@kirti',
      action: 'mentioned you in College Gang',
      time: '4 hours ago',
      avatar:
        'https://img.freepik.com/free-photo/beautiful-girl-stands-park_8353-5084.jpg?size=626&ext=jpg&ga=GA1.1.2008272138.1726963200&semt=ais_hybrid',
    },
  ];

  suggestions = [
    {
      id: 1,
      name: 'Abhiman Singh',
      avatar:
        'https://img.freepik.com/free-photo/beautiful-girl-stands-park_8353-5084.jpg?size=626&ext=jpg&ga=GA1.1.2008272138.1726963200&semt=ais_hybrid',
    },
    {
      id: 2,
      name: 'Ved Prakash',
      avatar:
        'https://img.freepik.com/free-photo/beautiful-girl-stands-park_8353-5084.jpg?size=626&ext=jpg&ga=GA1.1.2008272138.1726963200&semt=ais_hybrid',
    },
    {
      id: 3,
      name: 'Amit Trivedi',
      avatar:
        'https://img.freepik.com/free-photo/beautiful-girl-stands-park_8353-5084.jpg?size=626&ext=jpg&ga=GA1.1.2008272138.1726963200&semt=ais_hybrid',
    },
    {
      id: 4,
      name: 'Vikash Raj',
      avatar:
        'https://img.freepik.com/free-photo/beautiful-girl-stands-park_8353-5084.jpg?size=626&ext=jpg&ga=GA1.1.2008272138.1726963200&semt=ais_hybrid',
    },
  ];
}
