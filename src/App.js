import {Route , Routes} from 'react-router-dom'

import React , {useState , useEffect } from 'react';

import Home from './pages/Home'
import ItemDetail from './pages/ItemDetail'

function App() {

    /* 카테고리 선택 */
    const [pcategory , setPcategory] = useState('animal')
    const [categoryNavigation , setCategoryNavigation] = useState([])

    const clickPcategory = (pcategory,pcategoryname) => {
        setPcategory(pcategory)

        let categoryNavigationList = [...categoryNavigation]

        switch (pcategory.split('-')[0]){
            case 'dog' :
                categoryNavigationList[0] = '강아지'
                break;
            case 'cat' :
                categoryNavigationList[0] = '고양이'
                break;
            case '고라니' :
                categoryNavigationList[0] = '고라니'
                break;                    
            case 'best' :
                categoryNavigationList[0] = '추천상품'
                break;

            default :
                return
        }

        if (pcategoryname === 'all') {categoryNavigationList[1] = 'all'}
        else {categoryNavigationList[1] = pcategoryname}
        
        setCategoryNavigation(categoryNavigationList)
    }    

    /*스크롤 이벤트 */

    const [ScrollY, setScrollY] = useState(0); // window 의 pageYOffset값을 저장 
    const [ScrollActive, setScrollActive] = useState(false); 

    const handleScroll = () => { 
        if(ScrollY > 120) {
            setScrollY(window.pageYOffset);
            setScrollActive(true);
        } else {
            setScrollY(window.pageYOffset);
            setScrollActive(false);
        }
    }

    useEffect(() => {
        function scrollListener() {  window.addEventListener("scroll", handleScroll); } //  window 에서 스크롤을 감시 시작
        scrollListener(); // window 에서 스크롤을 감시
        return () => { window.removeEventListener("scroll", handleScroll); }; //  window 에서 스크롤을 감시를 종료
    });


return (
    <Routes>
        <Route path="/"
        element={<Home 
        pcategory={pcategory} clickPcategory={clickPcategory} ScrollActive={ScrollActive} categoryNavigation={categoryNavigation}  />} />

        <Route path="/ItemDetail/:pcategory/:pid"
        element={<ItemDetail 
        ScrollActive={ScrollActive} categoryNavigation={categoryNavigation} />} />
    </Routes>
);
}

export default App;
