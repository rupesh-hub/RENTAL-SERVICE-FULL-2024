import { Component, Input, OnInit } from '@angular/core';
import { Chart } from 'chart.js/auto';

interface Purchase {
  id: number;
  date: string;
  product: string;
  price: number;
  status: 'Delivered' | 'Shipped' | 'Processing';
  image: string;
}

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss',
})
export class ProfileComponent implements OnInit {
  @Input() size: string = '24';
  @Input() color: string = 'currentColor';
  @Input() ariaLabel: string = 'View';

  @Input() purchaseHistory: Purchase[] = [
    {
      id: 1,
      date: '2024-09-20',
      product: 'Laptop',
      price: 1200,
      status: 'Delivered',
      image:
        'https://cdn-dynmedia-1.microsoft.com/is/image/microsoftcorp/PDP-Highlight-Consumer-Laptop-Go-3-Platinum-001:VP1-539x440',
    },
    {
      id: 2,
      date: '2024-09-15',
      product: 'Smartphone',
      price: 800,
      status: 'Shipped',
      image:
        'https://cdn-dynmedia-1.microsoft.com/is/image/microsoftcorp/PDP-Highlight-Consumer-Laptop-Go-3-Platinum-001:VP1-539x440',
    },
    {
      id: 3,
      date: '2024-09-10',
      product: 'Headphones',
      price: 150,
      status: 'Processing',
      image:
        'https://cdn-dynmedia-1.microsoft.com/is/image/microsoftcorp/PDP-Highlight-Consumer-Laptop-Go-3-Platinum-001:VP1-539x440',
    },
    {
      id: 4,
      date: '2024-09-05',
      product: 'Monitor',
      price: 300,
      status: 'Delivered',
      image:
        'https://cdn-dynmedia-1.microsoft.com/is/image/microsoftcorp/PDP-Highlight-Consumer-Laptop-Go-3-Platinum-001:VP1-539x440',
    },
  ];

  monthlyExpenses = [
    { month: 'Jan', expense: 1200 },
    { month: 'Feb', expense: 1800 },
    { month: 'Mar', expense: 1400 },
    { month: 'Apr', expense: 2200 },
    { month: 'May', expense: 1900 },
    { month: 'Jun', expense: 2400 },
    { month: 'Jul', expense: 1100 },
    { month: 'Aug', expense: 1300 },
    { month: 'Sep', expense: 900 },
    { month: 'Oct', expense: 3000 },
    { month: 'Nov', expense: 2700 },
    { month: 'Dec', expense: 2300 },
  ];

  ngOnInit() {
    this.createExpensesChart();
  }

  createExpensesChart() {
    const ctx = document.getElementById('expensesChart') as HTMLCanvasElement;
    new Chart(ctx, {
      type: 'line',
      data: {
        labels: this.monthlyExpenses.map((data) => data.month),
        datasets: [
          {
            label: 'Monthly Expenses',
            data: this.monthlyExpenses.map((data) => data.expense),
            fill: false,
            borderColor: 'rgb(75, 192, 192)',
            tension: 0.1,
          },
          {
            label: 'Amount ($)',
            data: this.monthlyExpenses.map((data) => data.expense),
            fill: false,
            borderColor: 'rgb(0, 0, 0)',
            tension: 0.1,
          },
        ],
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
          y: {
            beginAtZero: true,
          },
        },
      },
    });
  }

  isEditing = false;
  user = {
    firstName: 'John',
    lastName: 'Doe',
    email: 'john.doe@gmail.com',
    phone: '+1 234 567 8900',
    address: '123 Main St, Anytown, AN 12345',
    profilePic:
      'https://media.istockphoto.com/id/1159801465/photo/portrait-of-handsome-boy-posing-in-photo-studio.jpg?s=612x612&w=0&k=20&c=JL9do2qrawpp6U81nJcW-u6KHJsjTZRSTy-RwDoGGlw=',
    browsedItems: 2380,
    orders: 32,
  };

  toggleEdit() {
    this.isEditing = !this.isEditing;
    if (!this.isEditing) {
      // Here you would typically save the user data
      console.log('Saving user data:', this.user);
    }
  }

  onProfilePicChange(event: Event) {
    const file = (event.target as HTMLInputElement).files?.[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e: ProgressEvent<FileReader>) => {
        this.user.profilePic = e.target?.result as string;
      };
      reader.readAsDataURL(file);
    }
  }
}
