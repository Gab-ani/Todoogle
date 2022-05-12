# Todoogle

A software designed to organise curiosity.

Have you ever heard of must-to-read book but forget about it in an hour?

Have you ever been curious to research a theme closer, but wasn't able to do it during a moment?

Most people see "save post" as a solution, but no saved post can remind about itself, thus leading to great graveyards of recipes, book/film lists etc.

With todoogle you can set something interesting to remind you about itself. It's as easy as google something, but you also setting a date and time, for example, todays 21:30. Todoogle will save your queries and send you an email containing your questions. Even better, not only it will remind you about them, the text is hyperlinked to exact same query in google search.
![изображение](https://user-images.githubusercontent.com/25298003/166162928-6bf75932-5313-4d0d-8afd-db8b5fbc4e2d.png)
![изображение](https://user-images.githubusercontent.com/25298003/166163609-7ae22105-535e-4891-a946-53d1af2a2220.png)

________________________________________________________________________________________________________________________________________________________
# 09.05.22 scheme outdated
![Untitled Diagram drawio(3)](https://user-images.githubusercontent.com/25298003/166116059-e19fa06e-1f4a-4995-ac32-031e1f842a03.png)
______________________________________________________________________________________________________
# Future

Immediate objectives:

~~1) Email confirmation with tokens etc, because rn the service is kinda spam-machine in a wrong hands.~~

~~2) Becoming RESTful. I'm planning to stop uploading ~~master branch for a while and continue work in "becoming RESTful" branch.
3) Adding the "default sending time button" and update users to store their preferences. My view on the product is less of an organiser and more of "get a long email of cool things on sunday morning 12 am".
4) Add a "letter to the future" functionality, because it's actually 90% done, extends functionality greatly and serves as a motivation to learn frontend.
5) Try to implement parallel email sending, as I learned, it can't be simply done by slapping async on top of the sending method, but without an ability to *hypothetical* send thousands emails per minute, the service practically unscalable => useless.

Joke gone too far objectives:
1) Create actual frontend, lol.
2) Supporting telegram as an alternative communication channel.
3) Implement ability of creating custom "feed mailing", sort of "make yourself a newsletter/memescroll" where service provides scheduling and organising but all the content is still heavily defined by user.
