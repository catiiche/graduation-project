package com.example.project.thread;

import com.example.project.entity.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * class ClientCollections
 * training set of clients
 *
 * @author Kate Shkuratova
 * @version 1.0
 */
public class ClientCollections {
    List<Client> approvedClients = new ArrayList<>();

    public List<Client> getApprovedClients() {
        return approvedClients;
    }

    public void createClients() {
        Client client = new Client(1237897891L, "Ivan", "Ivanov", 45, "Smolensk",
                4, 45000, true);
        client.setStatus(false);
        approvedClients.add(client);

        Client client1 = new Client(3334567890L, "Aleksey", "Korshunov", 26, "Moscow",
                0, 60000, true);
        client1.setStatus(true);
        approvedClients.add(client1);

        Client client2 = new Client(1234524500L, "Igor", "Petrov", 18, "Moscow",
                2, 160000, true);
        client2.setStatus(true);
        approvedClients.add(client2);

        Client client3 = new Client(1234561110L, "Irina", "Borisov", 68, "Sochi",
                6, 50000, false);
        client3.setStatus(false);
        approvedClients.add(client3);

        Client client4 = new Client(1289607091L, "Alexandra", "Ivanova", 40, "Moscow",
                0, 18000, false);
        client4.setStatus(true);
        approvedClients.add(client4);

        Client client5 = new Client(1230063290L, "Alena", "Saltanova", 36, "Moscow",
                1, 160000, true);
        client5.setStatus(true);
        approvedClients.add(client5);

        Client client6 = new Client(1030587800L, "Maxim", "Severyanov", 38, "Saint-Petersburg",
                1, 30000, false);
        client6.setStatus(true);
        approvedClients.add(client6);

        Client client7 = new Client(1230222000L, "Albert", "Janetov", 28, "Pushkin",
                2, 60000, true);
        client7.setStatus(false);
        approvedClients.add(client7);

        Client client8 = new Client(1234562221L, "Leonid", "Krabov", 55, "Novosibirsk",
                2, 17000, false);
        client8.setStatus(false);
        approvedClients.add(client8);

        Client client9 = new Client(1234589691L, "Valentina", "Doncova", 25, "Moscow",
                0, 49000, true);
        client9.setStatus(true);
        approvedClients.add(client9);

        Client client10 = new Client(1237417891L, "Sergey", "Safin", 27, "Pskov",
                0, 87000, false);
        client10.setStatus(true);
        approvedClients.add(client10);

        Client client11 = new Client(1333567871L, "Fedor", "Gradov", 45, "Voronezh",
                2, 56000, true);
        client11.setStatus(true);
        approvedClients.add(client11);

        Client client12 = new Client(1656567891L, "Georgy", "Krupovickiy", 23, "Moscow",
                2, 67700, false);
        client12.setStatus(false);
        approvedClients.add(client12);

        Client client13 = new Client(1111567891L, "Bogdan", "Mikhaylovsky", 45, "Lipetsk",
                0, 85000, false);
        client13.setStatus(true);
        approvedClients.add(client13);

        Client client14 = new Client(1234567771L, "Arthur", "Katamaranov", 34, "Moscow",
                0, 145000, false);
        client14.setStatus(true);
        approvedClients.add(client14);

        Client client15 = new Client(1233337891L, "Alexandra", "Trofimova", 55, "Saint-Petersburg",
                5, 85000, false);
        client15.setStatus(false);
        approvedClients.add(client15);

        Client client16 = new Client(1734777891L, "Maxim", "Olshevsky", 35, "Novosibirsk",
                0, 65000, false);
        client16.setStatus(false);
        approvedClients.add(client16);

        Client client17 = new Client(1234599991L, "Alexander", "Bogatyrev", 45, "Moscow",
                0, 95000, false);
        client17.setStatus(false);
        approvedClients.add(client17);

        Client client18 = new Client(1237777891L, "Boris", "Ivanov", 55, "Novosibirsk",
                2, 45000, true);
        client18.setStatus(true);
        approvedClients.add(client18);

        Client client19 = new Client(1364567891L, "Denis", "Korepanov", 25, "Saint-Petersburg",
                0, 35000, false);
        client19.setStatus(false);
        approvedClients.add(client19);

        Client client20 = new Client(1234747891L, "Irina", " Eletskaya", 33, "Moscow",
                2, 55000, false);
        client20.setStatus(true);
        approvedClients.add(client20);

        Client client21 = new Client(1234596891L, "Ildar", "Farkhutdinov", 48, "Moscow",
                1, 85000, false);
        client21.setStatus(true);
        approvedClients.add(client21);

        Client client22 = new Client(1265567890L, "Olga", "Bondarchuk", 36, "Saint-Petersburg",
                2, 55000, false);
        client22.setStatus(false);
        approvedClients.add(client22);

        Client client23 = new Client(1984567891L, "Tatyana", "Bryk", 23, "Smolensk",
                0, 25000, true);
        client23.setStatus(true);
        approvedClients.add(client23);

        Client client24 = new Client(1234567891L, "Alla", "Morozova", 67, "Saint-Petersburg",
                3, 49000, false);
        client24.setStatus(true);
        approvedClients.add(client24);

        Client client25 = new Client(1234533891L, "Irina", "Sergeeva", 47, "Novosibirsk",
                6, 70000, true);
        client25.setStatus(true);
        approvedClients.add(client25);

        Client client26 = new Client(1234567321L, "Alexander", "Okunev", 43, "Smolensk",
                2, 43000, true);
        client26.setStatus(false);
        approvedClients.add(client26);

        Client client27 = new Client(1267567891L, "Rodion", "Rodnyuk", 47, "Moscow",
                5, 245000, true);
        client27.setStatus(true);
        approvedClients.add(client27);

        Client client28 = new Client(1234564891L, "Andrey", "Neistov", 73, "Saint-Petersburg",
                0, 57000, true);
        client28.setStatus(false);
        approvedClients.add(client28);

        Client client29 = new Client(1234566381L, "Georgy", "Vishnevskov", 27, "Novosibirsk",
                0, 40000, false);
        client29.setStatus(false);
        approvedClients.add(client29);

        Client client30 = new Client(1239667891L, "Ignat", "Kaysin", 34, "Luga",
                1, 43000, true);
        client30.setStatus(false);
        approvedClients.add(client30);

        Client client31 = new Client(1234578891L, "Alina", "Ryumko", 54, "Saint-Petersburg",
                2, 49000, true);
        client31.setStatus(true);
        approvedClients.add(client31);

        Client client32 = new Client(1277567891L, "Alexander", "Nikolaev", 24, "Novosibirsk",
                0, 56000, true);
        client32.setStatus(true);
        approvedClients.add(client32);

        Client client33 = new Client(3698745632L, "Lubov", " Safarova", 29, "Moscow",
                0, 89000, true);
        client33.setStatus(true);
        approvedClients.add(client33);

        Client client34 = new Client(1634567891L, "Ivan", " Safarov", 75, "Saint-Petersburg",
                3, 26000, true);
        client34.setStatus(false);
        approvedClients.add(client34);

        Client client35 = new Client(123964567891L, "Daria", "Chizhova", 27, "Novosibirsk",
                0, 26000, true);
        client35.setStatus(false);
        approvedClients.add(client35);

        Client client36 = new Client(8934567891L, "Georgiy", "Burkov", 47, "Moscow",
                5, 89000, true);
        client36.setStatus(true);
        approvedClients.add(client36);

        Client client37 = new Client(1236367891L, "Larisa", "Ivanova", 58, "Saint-Petersburg",
                2, 68000, true);
        client37.setStatus(true);
        approvedClients.add(client37);

        Client client38 = new Client(1234547891L, "Oleg", "Korneev", 53, "Moscow",
                2, 67000, true);
        client38.setStatus(true);
        approvedClients.add(client38);

        Client client39 = new Client(1244567891L, "Egor", "Luchkin", 52, "Novosibirsk",
                2, 67000, true);
        client39.setStatus(false);
        approvedClients.add(client39);

        Client client40 = new Client(1434567891L, "Lev", "Serov", 36, "Smolensk",
                8, 96000, true);
        client40.setStatus(false);
        approvedClients.add(client40);
    }
}
