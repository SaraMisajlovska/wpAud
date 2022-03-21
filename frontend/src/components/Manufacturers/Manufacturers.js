import React from "react";


const manufacturers = (props) => {
    return (
        <div className={"container col-md-3 mt-5"}>
            <div className={"row"}>
                <div className={"row"}>
                    <table className={"table table-striped"}>
                        <thead>
                        <tr>
                            <td scope={"col"}>Name</td>
                            <td scope={"col"}>Address</td>
                        </tr>
                        </thead>
                        <tbody>
                        {props.manufacturers.map((term) => {
                            return (
                                <tr>
                                    <td>{term.name}</td>
                                    <td>{term.address}</td>
                                </tr>
                            );
                        })
                        }
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
}
export default manufacturers;