function showUserInfo() {
    fetch("http://localhost:8080/user/userList")
        .then(response => {
            if (!response.ok) {
                throw new Error('Ошибка сети');
            }
            return response.json();
        })
        .then(users => {
            console.log('User data:', users); // Печать структуры данных
            const tableBody = document.getElementById("userTable");
            tableBody.innerHTML = ""; // Очистка таблицы

            users.forEach(user => {
                console.log(user); // Печать каждого объекта пользователя
                const row = `
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.lastName}</td>
                <td>${user.age}</td>
                <td>${user.email}</td>
                <td>${Array.isArray(user.shortRole) ? user.shortRole.map(role => role.name).join(', ') : user.shortRole}</td>

            </tr>
        `;
                tableBody.innerHTML += row;
            });
        })
        .catch(error => console.error('Ошибка при загрузке данных:', error));
}

// Вызов функции при загрузке страницы
document.addEventListener("DOMContentLoaded", showUserInfo);