export interface User {
  userId?: number;
  name: string;
  username: string;
  password?: string;
  active: boolean;
  token?: string;
}

export interface Product {
  productId?: number;
  name: string;
  description: string;
  price: number;
}

export interface Customer {
  customerId?: number;
  name: string;
  cpf: string;
  phone: number;
}

export interface Order {
  orderId?: number;
  customerId: number;
  items: Product[];
  createdAt?: string;
  totalValue: number;
}
