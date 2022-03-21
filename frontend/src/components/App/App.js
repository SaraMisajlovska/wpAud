import './App.css';
import React, {Component} from "react";
import Manufacturers from "../Manufacturers/Manufacturers";
import EShopService from "../../repository/eshopRepository";
import {BrowserRouter as Router, Routes, Route, Navigate} from "react-router-dom";
import Categories from "../Categories/Categories";
import Products from "../Products/ProductList/Products";
import Header from "../Header/Header";
class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            manufacturers: [],
            categories: [],
            products: []
        }
    }

    loadManufacturers = () => {
        EShopService.fetchManufacturers()
            .then((data) => {
                this.setState({
                    manufacturers: data.data
                })
            });
    }
    loadCategories = () => {
        EShopService.fetchCategories()
            .then((data) => {
                this.setState({
                    categories: data.data
                })
            });
    }
    loadProducts = () => {
        EShopService.fetchProducts()
            .then((data) => {
                this.setState({
                    products: data.data
                })
            })
    }

    componentDidMount() {
        this.loadManufacturers();
        this.loadCategories();
        this.loadProducts();
    }

    render() {
        return (
            <Router>
                <Header/>
                <Routes>
                    <Route path={"/manufacturers"}
                           element={<Manufacturers manufacturers={this.state.manufacturers}/>}
                    />
                    <Route path={"/categories"}
                           element ={<Categories categories={this.state.categories}/>}
                    />
                    <Route path={"/products"}
                           element={<Products products={this.state.products}/>}
                    />
                    <Route path="*" element={<Navigate to={"/products"}/>}/>
                </Routes>
            </Router>
        );
    }
}

export default App;
