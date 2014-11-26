<html>
    <body>
    ${loginForm.message}
        <form action="#" th:action="@{/login}" th:object="${loginForm}" method="post">
            <table>
                <tr>
                    <td>Name:</td>
                    <td><input type="text" th:field="*{name}" /></td>
                    <td th:if="${#fields.hasErrors('name')}" th:errors="*{name}">Name Error</td>
                </tr>
                <tr>
                    <td>password:</td>
                    <td><input type="text" th:field="*{password}" /></td>
                    <td th:if="${#fields.hasErrors('password')}" th:errors="*{password}">password Error</td>
                </tr>
                <tr>
                    <td><button type="submit">Submit</button></td>
                </tr>
            </table>
        </form>
    </body>
</html>
