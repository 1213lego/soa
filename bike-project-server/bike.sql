create table if not exists Bike (
    serial varchar(12),
    type varchar(12),
    brand varchar(20),
    weight float4,
    price float4,
    purchaseDate date,
    primary key (serial)
);