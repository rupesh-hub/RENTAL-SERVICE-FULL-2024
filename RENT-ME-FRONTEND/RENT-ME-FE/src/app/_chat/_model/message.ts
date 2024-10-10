export interface Message {
  text: string;
  sender: 'me' | 'other';
  timestamp: Date;
  profilePic: string;
}