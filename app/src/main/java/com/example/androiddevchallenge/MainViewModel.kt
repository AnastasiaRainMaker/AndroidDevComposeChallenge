package com.example.androiddevchallenge

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    fun getPuppies() = arrayListOf(
        Puppy(
            "https://upload.wikimedia.org/wikipedia/commons/6/6e/Golde33443.jpg",
            "Buddy",
            "The friendliest puppy ever. Loves to play and cuddle."
        ),
        Puppy(
            "https://www.positivek9training.com.au/wp-content/uploads/2019/02/Puppy-at-puppy-obedience-school-600x600.jpg",
            "Rocky",
            "A real baddy. He never gets tired of play time."
        ),
        Puppy(
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSwzqzyaeWqxfQiCnOqpnd1V27Wr8MOaZtfGQ&usqp=CAU",
            "Luke",
            "A real baddy. He never gets tired of play time."
        ),
        Puppy(
            "https://www.vetmed.ucdavis.edu/sites/g/files/dgvnsk491/files/styles/sf_landscape_16x9/public/images/article/Riley_0.jpg?h=50ba2d86&itok=Uvt9NGKb",
            "Manny",
            "A real baddy. He never gets tired of play time."
        ),
        Puppy(
            "https://i2-prod.mirror.co.uk/science/article22026077.ece/ALTERNATES/s615/0_JS211927677.jpg",
            "Toy",
            "A real baddy. He never gets tired of play time."
        ),
        Puppy(
            "https://e3.365dm.com/20/08/2048x1152/skynews-puppies-lockdown_5065877.jpg",
            "Diamond",
            "A real baddy. He never gets tired of play time."
        ),
        Puppy(
            "https://media.npr.org/assets/img/2020/01/15/wolffetch1_custom-54af0a0e9e81a6d00361b2b51a480f3d319a4e16-s800-c85.jpg",
            "Ricky",
            "A real baddy. He never gets tired of play time."
        ),
        Puppy(
            "https://images.saymedia-content.com/.image/t_share/MTc0MjEyNDMwOTgwMTMwNjg0/dog-behavior-what-is-a-puppy-license.gif",
            "Sue",
            "A real baddy. He never gets tired of play time."
        )
    )
}