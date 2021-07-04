// show hide option
const header__right__nav__content = document.querySelector('.header--right__nav__content');
const header__right__nav__content__options = document.querySelector('.header--right__nav__content__options');
const header__right__nav__icon = document.querySelector('.header--right__nav__icon');
document.addEventListener('click',(e)=>{
    let isClickInsideOption = header__right__nav__content.contains(e.target);
    if(!isClickInsideOption){
        //do something click is outside specified element
        header__right__nav__content__options.style.display = 'none';
    }
})
header__right__nav__content__options.style.display = 'none';
header__right__nav__icon.addEventListener('click', ()=>{
    if(header__right__nav__content__options.style.display == 'none'){
        header__right__nav__content__options.style.display = 'block';
    }else{
        header__right__nav__content__options.style.display = 'none';
    }
    
})