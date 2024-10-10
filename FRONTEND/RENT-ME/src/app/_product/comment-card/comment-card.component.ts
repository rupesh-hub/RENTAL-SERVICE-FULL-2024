import { Component } from '@angular/core';

@Component({
  selector: 'app-comment-card',
  templateUrl: './comment-card.component.html',
  styleUrl: './comment-card.component.scss',
})
export class CommentCardComponent {
  
  public userAvatar:string =
    'https://media.istockphoto.com/id/1476170969/photo/portrait-of-young-man-ready-for-job-business-concept.webp?b=1&s=170667a&w=0&k=20&c=FycdXoKn5StpYCKJ7PdkyJo9G5wfNgmSLBWk3dI35Zw=';
  public newComment:string = '';
  public comments:any = [
    {
      name: 'John Doe',
      avatar: this.userAvatar,
      text: 'This is a comment from John.',
      time: new Date(),
      likes: 1,
      dislikes: 10,
      liked: true,
      disliked: false,
      showReply: false,
      replyText: '',
      replies: [
        {
          avatar: this.userAvatar,
          name: 'Rupesh sharma',
          time: new Date(),
          text: 'This is reply to other comment.',
          liked: true,
          disliked: false,
          likes: 12,
          dislikes: 0
        },
        {
          avatar: this.userAvatar,
          name: 'Rupesh sharma',
          time: new Date(),
          text: 'This is reply to other comment.',
          liked: true,
          disliked: false,
          likes: 12,
          dislikes: 0
        },
      ],
    },
    {
      name: 'Jane Smith',
      avatar: this.userAvatar,
      text: 'This is a comment from Jane.',
      time: new Date(),
      likes: 0,
      dislikes: 1,
      liked: false,
      disliked: true,
      showReply: false,
      replyText: '',
      replies: [],
    },
  ];

  addComment() {
    if (this.newComment.trim()) {
      this.comments.push({
        name: 'Current User',
        avatar: this.userAvatar,
        text: this.newComment,
        time: new Date(),
        likes: 0,
        dislikes: 0,
        liked: false,
        disliked: false,
        showReply: false,
        replyText: '',
        replies: [],
      });
      console.log(this.comments)
      this.newComment = '';
    }
  }

  toggleReply(comment: any) {
    comment.showReply = !comment.showReply;
  }

  addReply(comment: any) {
    if (comment.replyText.trim()) {
      comment.replies.push({
        name: 'Current User',
        avatar: this.userAvatar,
        text: comment.replyText,
        time: new Date(),
        likes: 0,
        dislikes: 0,
        liked: false,
        disliked: false,
      });
      comment.replyText = '';
      comment.showReply = false;
    }
  }

  likeComment(comment: any) {
    if (!comment.liked) {
      comment.likes++;
      comment.liked = true;
      if (comment.disliked) {
        comment.dislikes--;
        comment.disliked = false;
      }
    }
  }

  dislikeComment(comment: any) {
    if (!comment.disliked) {
      comment.dislikes++;
      comment.disliked = true;
      if (comment.liked) {
        comment.likes--;
        comment.liked = false;
      }
    }
  }
}
